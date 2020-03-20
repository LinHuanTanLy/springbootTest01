package com.ly.shop.controller.cart;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@Api(value = "购物车")
@RequestMapping("api/cart/")
@Slf4j
public class CartController {
}
