package com.ly.shop.vo.cart;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 添加
 */
@Data
public class CartAddVo {
    @ApiModelProperty(value = "商品id", required = true)
    private Long productId;
    @ApiModelProperty(value = "用户id", required = true)
    private Long userId;
    @ApiModelProperty(value = "数量", required = true)
    private Integer productNum;
    @ApiModelProperty(value = "是否删除 0-未删除 1-删除", hidden = true)
    private Integer isDeleted;
    @ApiModelProperty(value = "店铺ID", hidden = true)
    private Integer storeId;
    @ApiModelProperty(value = "店铺编码", hidden = true)
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
}
