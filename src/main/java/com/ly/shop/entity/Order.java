package com.ly.shop.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 订单
 */
@Data
public class Order {
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;
    @ApiModelProperty(value = "订单号", required = true)
    private String orderSn;
    @ApiModelProperty(value = "商品id", required = true)
    private Long productId;
    @ApiModelProperty(value = "用户id", required = true)
    private Long userId;
    @ApiModelProperty(value = "数量", required = true)
    private Integer productNum;
    @ApiModelProperty(value = "是否删除 0-未删除 1-删除", required = true)
    private Integer isDeleted;
    @ApiModelProperty(value = "店铺ID", required = true)
    private Integer storeId;
    @ApiModelProperty(value = "店铺编码", required = true)
    private String storeCode;
    @ApiModelProperty(value = "创建者")
    private String creator;
    @ApiModelProperty(value = "创建者ID")
    private Long creatorId;
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
    @ApiModelProperty(value = "更新用户")
    private String lastOperator;
    @ApiModelProperty(value = "更新用户Id")
    private Integer lastOperatorId;
    @ApiModelProperty(value = "更新时间")
    private String updateTime;
    @ApiModelProperty(value = "订单状态 0-未支付 1-已支付 2-已取消 3-已退款")
    private Integer status;
}
