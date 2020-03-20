package com.ly.shop.controller.product;

import com.ly.shop.api.CommResult;
import com.ly.shop.base.BaseController;
import com.ly.shop.constant.ShopConstant;
import com.ly.shop.entity.User;
import com.ly.shop.service.ProductService;
import com.ly.shop.vo.product.ProductAddVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    private void initUserInfo(User user, ProductAddVo addVo) {
        addVo.setCreatedTime(LocalDateTime.now());
        addVo.setCreator(user.getUserName());
        addVo.setCreatorId(user.getId());
        addVo.setLastOperator(user.getUserName());
        addVo.setLastOperatorId(Math.toIntExact(user.getId()));
        addVo.setUpdateTime(LocalDateTime.now());
    }
}
