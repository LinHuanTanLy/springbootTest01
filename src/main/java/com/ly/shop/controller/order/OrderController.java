package com.ly.shop.controller.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.shop.api.CommResult;
import com.ly.shop.api.ErrCode;
import com.ly.shop.base.BaseController;
import com.ly.shop.constant.ShopConstant;
import com.ly.shop.entity.Order;
import com.ly.shop.entity.Product;
import com.ly.shop.entity.User;
import com.ly.shop.exception.BaseException;
import com.ly.shop.exception.impl.*;
import com.ly.shop.service.OrderService;
import com.ly.shop.service.ProductService;
import com.ly.shop.service.UserService;
import com.ly.shop.utils.CommUtils;
import com.ly.shop.vo.order.OrderAddVo;
import com.ly.shop.vo.user.UserUpdateVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Validated
@Api(value = "订单")
@RequestMapping("api/order/")
@Slf4j
public class OrderController extends BaseController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @GetMapping("/{status}")
    @ApiOperation("根据状态获取订单")
    public CommResult<PageInfo<Order>> orders(@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                              @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                              HttpSession httpSession, @ApiParam("订单状态 0-未支付 1-已支付 2-已取消 3-已退款") @PathVariable String status) {
        User user = getUserFromSession(httpSession);
        PageHelper.startPage(pageNum, pageSize);
        List<Order> list = orderService.orders(user.getId(), status);
        return CommResult.suc(new PageInfo<>(list));
    }

    @PutMapping("pay/{id}")
    @ApiOperation("订单支付")
    public CommResult<Boolean> payOrder(@PathVariable int id) {
        //1. 校验订单
        Order order = verifyOrderInfo(id);
        //2. 只有订单处于未付款状态 才可以付款
        if (!order.getStatus().equals(ShopConstant.OrderStatus.NOT_PAYED)) {
            throw new CommException("该订单状态异常，不能支付，请联系管理员", ErrCode.REQUEST_ERR);
        }
        int result = orderService.statusToPayed(id);
        if (result == 1) {
            return CommResult.suc(true);
        } else {
            return CommResult.fail(false);
        }
    }

    @PutMapping("cancel/{id}")
    @ApiOperation("订单取消")
    public CommResult<Boolean> cancelOrder(@PathVariable int id) {
        //1. 校验订单
        Order order = verifyOrderInfo(id);
        //2. 只有订单处于非取消状态 才可以取消
        if (order.getStatus().equals(ShopConstant.OrderStatus.IS_CANCELED)) {
            throw new CommException("该订单已经取消", ErrCode.REQUEST_ERR);
        }
        int result = orderService.statusToCancel(id);
        if (result == 1) {
            return CommResult.suc(true);
        } else {
            return CommResult.fail(false);
        }
    }

    @PutMapping("refunded/{id}")
    @ApiOperation("订单退款")
    public CommResult<Boolean> refundedOrder(@PathVariable int id) {
        //1. 校验订单
        Order order = verifyOrderInfo(id);
        //2. 只有订单处于已支付状态 才可以申请退款
        if (!order.getStatus().equals(ShopConstant.OrderStatus.IS_PAYED)) {
            throw new CommException("该订单未支付，无法退款", ErrCode.REQUEST_ERR);
        }
        int result = orderService.statusToRefunded(id);
        if (result == 1) {
            return CommResult.suc(true);
        } else {
            return CommResult.fail(false);
        }
    }

    @PostMapping("/")
    @ApiOperation("下单")
    @Transactional //注解事务
    public CommResult<Boolean> addOrders(@RequestBody OrderAddVo orderAddVo) {
        // 1.查下是否是有效用户
        User user = userService.findById(orderAddVo.getUserId());
        if (user == null) {
            throw new UserNotFoundException();
        }
        // TODO: 2020/3/20 用户钱不够  应该抛出异常
        addUserInfo(user, orderAddVo);

        // 2.查下是否是有效商品
        Product product = productService.findOne(orderAddVo.getProductId());
        if (product == null) {
            //商品没找到 抛出商品找不到异常
            throw new ProductNotFoundException();
        }
        if (product.getStockNum() <= 0 || product.getStockNum() < orderAddVo.getProductNum()) {
            // 库存不足 抛异常
            throw new ProductOutOfException();
        }
        if (product.getPrice().multiply(BigDecimal.valueOf(orderAddVo.getProductNum())).compareTo(orderAddVo.getPrice()) != 0) {
            // 商品价格*订单商品数 ！= 订单价格，报错 抛异常
            throw new OrderErrException();
        }
        // 3.生成订单号和给默认状态
        orderAddVo.setOrderSn(CommUtils.getUUID());
        orderAddVo.setIsDeleted(ShopConstant.isDelete.NORMAL);
        orderAddVo.setStatus(ShopConstant.OrderStatus.NOT_PAYED);
        // 4.下单
        log.info("the orderAddVo is " + orderAddVo);
        int result = orderService.order(orderAddVo);
        // 5.下单成功后，更新商品库存，更新用户历史订单数
        // 拿到最新的库存
        int stockNum = product.getStockNum() - orderAddVo.getProductNum();
        // 更新商品表库存
        productService.updateStock(orderAddVo.getProductId(), stockNum);
        // 更新用户历史下单数
        int hisOrderQuantity = user.getHisOrderQuantity() + 1;
        UserUpdateVo userUpdateVo = new UserUpdateVo();
        log.info("the hisOrderQuantity is " + hisOrderQuantity);
        userUpdateVo.setHisOrderQuantity(hisOrderQuantity);
        int addHisCode = userService.updateUsr(userUpdateVo);
        log.info("the adHisCode is " + addHisCode);
        if (result == 1) {
            return CommResult.suc(true);
        } else {
            return CommResult.fail(ErrCode.ADD_ERROR.getCode(), "下单失败");
        }
    }


    /**
     * 校验订单是否存在
     *
     * @param id
     * @return
     */
    private Order verifyOrderInfo(int id) {
        Order order = orderService.order(id);
        if (order == null) {
            throw new CommException("订单不存在", ErrCode.REQUEST_ERR);
        }
        return order;
    }

    /**
     * 添加数据
     *
     * @param user
     * @param orderAddVo
     */
    private void addUserInfo(User user, OrderAddVo orderAddVo) {
        if (user != null) {
            orderAddVo.setCreatedTime(LocalDateTime.now());
            orderAddVo.setUpdateTime(LocalDateTime.now());
            orderAddVo.setCreator(user.getUserName());
            orderAddVo.setCreatorId(user.getId());
            orderAddVo.setLastOperator(user.getUserName());
            orderAddVo.setLastOperatorId(user.getId());
        }

    }
}
