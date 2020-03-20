package com.ly.shop.mapper.order;

import com.ly.shop.entity.Order;
import com.ly.shop.mapper.order.provider.OrderProvider;
import com.ly.shop.mapper.user.provider.UserProvider;
import com.ly.shop.vo.order.OrderAddVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderMapper {
    @SelectProvider(type = OrderProvider.class, method = "selectOrder")
    @Results(id = "orderIdStatus", value = {
            @Result(column = "order_sn", property = "orderSn"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "product_id", property = "productId"),
            @Result(column = "product_num", property = "productNum"),
            @Result(column = "is_deleted", property = "isDeleted"),
            @Result(column = "store_id", property = "storeId"),
            @Result(column = "store_code", property = "storeCode"),
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "status", property = "status"),
            @Result(column = "creator", property = "creator"),
            @Result(column = "created_time", property = "createdTime"),
            @Result(column = "last_operator", property = "lastOperator"),
            @Result(column = "last_operator_id", property = "lastOperatorId"),
            @Result(column = "update_time", property = "updateTime"),
    })
    List<Order> findOrders(long userId, String status);

    @Select("SELECT * from app_order where id= #{id} and is_deleted = #{status}")
    @Results(id = "orderId", value = {
            @Result(column = "order_sn", property = "orderSn"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "product_id", property = "productId"),
            @Result(column = "product_num", property = "productNum"),
            @Result(column = "is_deleted", property = "isDeleted"),
            @Result(column = "store_id", property = "storeId"),
            @Result(column = "store_code", property = "storeCode"),
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "status", property = "status"),
            @Result(column = "creator", property = "creator"),
            @Result(column = "created_time", property = "createdTime"),
            @Result(column = "last_operator", property = "lastOperator"),
            @Result(column = "last_operator_id", property = "lastOperatorId"),
            @Result(column = "update_time", property = "updateTime"),
    })
    Order findOrder(int id, int status);


    @Update("update app_order set status = #{status} where id = #{id}")
    int updateOrderStatus(int id, int status);

    @Update("update app_order set is_deleted = #{isDeleted} where id = #{id}")
    int deleteOrder(int id, int isDeleted);

    @Insert("INSERT INTO APP_ORDER (order_sn,user_id,product_id,product_num,store_id,store_code,creator_id," +
            "creator,created_time,last_operator_id,last_operator,update_time,status,price,is_deleted) values (#{orderSn},#{userId},#{productId},#{productNum}," +
            "#{storeId},#{storeCode},#{creatorId},#{creator},#{createdTime},#{lastOperatorId},#{lastOperator},#{updateTime},#{status},#{price},#{isDeleted})")
    int add(OrderAddVo orderAddVo);
}
