package com.ly.shop.vo.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品添加
 */
@Data
public class ProductAddVo {
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
    @ApiModelProperty(value = "店铺ID", required = true)
    private Integer storeId;
    @ApiModelProperty(value = "店铺编码", required = true)
    private Integer storeCode;
    @ApiModelProperty(value = "创建者", hidden = true)
    private String creator;
    @ApiModelProperty(value = "创建者ID", hidden = true)
    private Long creatorId;
    @ApiModelProperty(value = "创建时间", hidden = true)
    private LocalDateTime createdTime;
    @ApiModelProperty(value = "更新用户", hidden = true)
    private String lastOperator;
    @ApiModelProperty(value = "更新用户Id", hidden = true)
    private Integer lastOperatorId;
    @ApiModelProperty(value = "更新时间", hidden = true)
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "是否删除 0-未删除 1-删除", hidden = true)
    private Integer isDeleted;
}
