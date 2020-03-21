package com.ly.shop.controller.cart;

import com.ly.shop.api.CommResult;
import com.ly.shop.constant.ShopConstant;
import com.ly.shop.entity.Product;
import com.ly.shop.entity.User;
import com.ly.shop.exception.impl.ProductNotFoundException;
import com.ly.shop.exception.impl.ProductOutOfException;
import com.ly.shop.exception.impl.UserNotFoundException;
import com.ly.shop.service.CartService;
import com.ly.shop.service.ProductService;
import com.ly.shop.service.UserService;
import com.ly.shop.vo.cart.CartAddVo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Validated
@Api(value = "购物车")
@RequestMapping("api/cart/")
@Slf4j
public class CartController {


    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;

    @PostConstruct
    public void init() {
        /// 把商品表丢到redis中
        List<Product> productList = productService.products();
        for (Product p :
                productList) {
            stringRedisTemplate.opsForValue().set(ShopConstant.REDIS_PRODUCT_KEY + p.getId(), String.valueOf(p.getStockNum()));


            log.info("the product info is " + p.getId() + " and the stock num is " + p.getStockNum());
        }
    }

    @ApiOperation("添加购物车")
    @PostMapping("/redis/")
    public CommResult<Boolean> postAddCartWithRedis(@RequestBody CartAddVo cartAddVo) {
        Long stockRedis = stringRedisTemplate.opsForValue().decrement(ShopConstant.REDIS_PRODUCT_KEY + cartAddVo.getProductId(), cartAddVo.getProductNum());
        if (stockRedis == null || stockRedis.intValue() < 0) {
            stringRedisTemplate.opsForValue().increment(ShopConstant.REDIS_PRODUCT_KEY + cartAddVo.getProductId(), cartAddVo.getProductNum());
            throw new ProductOutOfException();
        }

        /// 校验用户
        User user = userService.findById(cartAddVo.getUserId());
        if (user == null) {
            stringRedisTemplate.opsForValue().increment(ShopConstant.REDIS_PRODUCT_KEY + cartAddVo.getProductId(), cartAddVo.getProductNum());
            throw new UserNotFoundException();
        }
        cartAddVo.setCreatedTime(LocalDateTime.now());
        cartAddVo.setUpdateTime(LocalDateTime.now());
        cartAddVo.setCreator(user.getUserName());
        cartAddVo.setCreatorId(user.getId());
        cartAddVo.setLastOperator(user.getUserName());
        cartAddVo.setLastOperatorId(user.getId());

        /// 校验商品
        Product product = productService.findOne(cartAddVo.getProductId());


        if (product == null) {
            stringRedisTemplate.opsForValue().increment(ShopConstant.REDIS_PRODUCT_KEY + cartAddVo.getProductId(), cartAddVo.getProductNum());
            throw new ProductNotFoundException();
        }
        if (product.getStockNum() <= 0 || product.getStockNum() < cartAddVo.getProductNum()) {
            stringRedisTemplate.opsForValue().increment(ShopConstant.REDIS_PRODUCT_KEY + cartAddVo.getProductId(), cartAddVo.getProductNum());
            throw new ProductOutOfException();
        }

        cartAddVo.setStoreCode(product.getStoreCode());
        cartAddVo.setStoreId(product.getStoreId());
        cartAddVo.setIsDeleted(ShopConstant.isDelete.NORMAL);

        int result = cartService.addCart(cartAddVo);
        if (result == 1) {
            /// 加入购物车成功，商品库存-1
            productService.updateStock(product.getId(), product.getStockNum());
            return CommResult.suc(true);
        } else {
            stringRedisTemplate.opsForValue().increment(ShopConstant.REDIS_PRODUCT_KEY + cartAddVo.getProductId(), cartAddVo.getProductNum());
            return CommResult.fail(false);
        }

    }

    @ApiOperation("添加购物车")
    @PostMapping("")
    public CommResult<Boolean> postAddCart(@RequestBody CartAddVo cartAddVo) {
        /// 校验用户
        User user = userService.findById(cartAddVo.getUserId());
        if (user == null) {
            throw new UserNotFoundException();
        }
        cartAddVo.setCreatedTime(LocalDateTime.now());
        cartAddVo.setUpdateTime(LocalDateTime.now());
        cartAddVo.setCreator(user.getUserName());
        cartAddVo.setCreatorId(user.getId());
        cartAddVo.setLastOperator(user.getUserName());
        cartAddVo.setLastOperatorId(user.getId());

        /// 校验商品
        Product product = productService.findOne(cartAddVo.getProductId());


        if (product == null) {
            throw new ProductNotFoundException();
        }
        if (product.getStockNum() <= 0 || product.getStockNum() < cartAddVo.getProductNum()) {
            throw new ProductOutOfException();
        }

        cartAddVo.setStoreCode(product.getStoreCode());
        cartAddVo.setStoreId(product.getStoreId());
        cartAddVo.setIsDeleted(ShopConstant.isDelete.NORMAL);

        int result = cartService.addCart(cartAddVo);
        if (result == 1) {
            /// 加入购物车成功，商品库存-1
            productService.updateStock(product.getId(), product.getStockNum());
            return CommResult.suc(true);
        } else {
            return CommResult.fail(false);
        }

    }

}
