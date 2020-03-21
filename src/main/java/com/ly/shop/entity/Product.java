package com.ly.shop.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品
 */
@Data
public class Product {

    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;

    @ApiModelProperty(value = "商品名称", required = true)
    private String productName;

    @ApiModelProperty(value = "编码", required = true)
    private String productCode;

    @ApiModelProperty(value = "主图url", required = true)
    private String mainImg;

    @ApiModelProperty(value = "价格", required = true)
    private BigDecimal price;

    @ApiModelProperty(value = "原价", required = true)
    private BigDecimal originPrice;

    @ApiModelProperty(value = "库存数量", required = true)
    private int stockNum;

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
}
