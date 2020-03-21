package com.ly.shop.mapper.cart;

import com.ly.shop.vo.cart.CartAddVo;
import org.apache.ibatis.annotations.Insert;

public interface CartMapper {


    @Insert("INSERT INTO APP_CART (product_id,user_id,product_num,is_deleted,store_id,store_code,creator,creator_id,created_time,last_operator,last_operator_id,update_time) VALUES (#{productId},#{userId},#{productNum},#{isDeleted},#{storeId},#{storeCode},#{creator},#{creatorId}," +
            "#{createdTime},#{lastOperator},#{lastOperatorId},#{updateTime})")
    int addCart(CartAddVo cartAddVo);
}
