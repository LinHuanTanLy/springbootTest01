package com.ly.shop.controller.product;

import com.ly.shop.api.CommResult;
import com.ly.shop.base.BaseController;
import com.ly.shop.constant.ShopConstant;
import com.ly.shop.entity.User;
import com.ly.shop.service.ProductService;
import com.ly.shop.vo.product.ProductAddVo;
import com.ly.shop.vo.product.ProductModifyVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RestController
@Validated
@Api(value = "商品")
@RequestMapping("api/product/")
@Slf4j
public class ProductController extends BaseController {


    @Autowired
    ProductService productService;


    @PostMapping("/")
    @ApiOperation("添加商品")
    public CommResult<Boolean> product(HttpSession httpSession, @RequestBody ProductAddVo addVo) {
        User user = getUserFromSession(httpSession);
        initUserInfo(user, addVo);
        addVo.setIsDeleted(ShopConstant.isDelete.NORMAL);
        int result = productService.addProduct(addVo);
        if (result == 1) {
            return CommResult.suc(true);
        } else {
            return CommResult.fail(false);
        }
    }

    @PutMapping("/")
    @ApiOperation("修改商品")
    public CommResult<Boolean> modify(HttpSession httpSession, @RequestBody ProductModifyVo modifyVo) {
        User user = getUserFromSession(httpSession);
        initUserInfo(user, modifyVo);
        modifyVo.setIsDeleted(ShopConstant.isDelete.NORMAL);
        int resultCode = productService.modifyProduct(modifyVo);
        if (resultCode == 1) {
            return CommResult.suc(true);
        } else {
            return CommResult.fail(false);
        }
    }


    private void initUserInfo(User user, ProductAddVo addVo) {
        addVo.setCreatedTime(LocalDateTime.now());
        addVo.setCreator(user.getUserName());
        addVo.setCreatorId(user.getId());
        addVo.setLastOperator(user.getUserName());
        addVo.setLastOperatorId(Math.toIntExact(user.getId()));
        addVo.setUpdateTime(LocalDateTime.now());
    }

    private void initUserInfo(User user, ProductModifyVo addVo) {

        addVo.setLastOperator(user.getUserName());
        addVo.setLastOperatorId(Math.toIntExact(user.getId()));
        addVo.setUpdateTime(LocalDateTime.now());
    }
}
