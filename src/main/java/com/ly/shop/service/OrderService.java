package com.ly.shop.service;

import com.ly.shop.constant.ShopConstant;
import com.ly.shop.entity.Order;
import com.ly.shop.mapper.order.OrderMapper;
import com.ly.shop.vo.order.OrderAddVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    /**
     * 根据status拉取数据，如果没有状态就拉全部
     *
     * @param status
     * @return
     */
    public List<Order> orders(long userId, String status) {
        return orderMapper.findOrders(userId, status);
    }

    @Transactional
    public int order(OrderAddVo orderAddVo) {
        return orderMapper.add(orderAddVo);
    }

    /**
     * 获取订单详情
     *
     * @param id
     * @return
     */
    public Order order(int id) {
        return orderMapper.findOrder(id, ShopConstant.isDelete.NORMAL);
    }

    /**
     * 更新订单状态--变为已付款
     *
     * @param id
     * @return
     */
    public int statusToPayed(int id) {
        return orderMapper.updateOrderStatus(id, ShopConstant.OrderStatus.IS_PAYED);
    }

    /**
     * 更新订单状态--变为取消
     *
     * @param id
     * @return
     */
    public int statusToCancel(int id) {
        return orderMapper.updateOrderStatus(id, ShopConstant.OrderStatus.IS_CANCELED);
    }

    /**
     * 更新订单状态--变为已退款
     *
     * @param id
     * @return
     */
    public int statusToRefunded(int id) {
        return orderMapper.updateOrderStatus(id, ShopConstant.OrderStatus.REFUNDED);
    }

    /**
     * 删除订单
     *
     * @param id
     * @return
     */
    public boolean delete(int id) {
        return true;
    }

}
