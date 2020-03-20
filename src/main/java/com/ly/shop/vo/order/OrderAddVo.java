package com.ly.shop.vo.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单添加vo
 */
@Data
public class OrderAddVo {
    @ApiModelProperty(value = "订单号", required = true, hidden = true)
    private String orderSn;
    @ApiModelProperty(value = "商品id", required = true)
    private Long productId;
    @ApiModelProperty(value = "用户id", required = true)
    private Long userId;
    @ApiModelProperty(value = "数量", required = true)
    private Integer productNum;
    @ApiModelProperty(value = "店铺ID", required = true)
    private Integer storeId;
    @ApiModelProperty(value = "店铺编码", required = true)
    private String storeCode;
    @ApiModelProperty(value = "创建者", hidden = true)
    private String creator;
    @ApiModelProperty(value = "创建者ID", hidden = true)
    private Long creatorId;
    @ApiModelProperty(value = "创建时间", hidden = true)
    private LocalDateTime createdTime;
    @ApiModelProperty(value = "更新用户", hidden = true)
    private String lastOperator;
    @ApiModelProperty(value = "更新用户Id", hidden = true)
    private Long lastOperatorId;
    @ApiModelProperty(value = "更新时间", hidden = true)
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "订单状态 0-未支付 1-已支付 2-已取消 3-已退款", hidden = true)
    private Integer status;
    @ApiModelProperty(value = "金额")
    private BigDecimal price;
    @ApiModelProperty(value = "是否删除 0-未删除 1-删除", hidden = true)
    private Integer isDeleted;
}
