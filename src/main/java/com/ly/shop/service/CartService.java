package com.ly.shop.service;


import com.ly.shop.mapper.cart.CartMapper;
import com.ly.shop.vo.cart.CartAddVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    CartMapper cartMapper;

    public int addCart(CartAddVo cartAddVo) {
        return cartMapper.addCart(cartAddVo);
    }
}
