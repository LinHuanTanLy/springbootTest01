package com.ly.shop.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class UserRegisterVo {
    @NotBlank(message = "用户名字不可以为空")
    @ApiModelProperty(value = "用户名称", required = true)
    private String userName;
    @NotBlank(message = "用户密码不可以为空")
    @ApiModelProperty(value = "用户密码", required = true)
    private String passWord;

    @NotBlank(message = "手机不可以为空")
    @ApiModelProperty(value = "手机", required = true)
    private String userMobile;
    @Max(3)
    @Min(0)
    @ApiModelProperty(value = "用户来源  0：默认  1：企业微信  2：微信  3：QQ", required = true)
    private Integer userSource;
    @NotBlank(message = "用户来源标识不可以为空")
    @ApiModelProperty(value = "用户来源标识，如企业微信成员id/微信openid", required = true)
    private String userSourceIdent;
//    @ApiModelProperty(value = "版本号", required = true)
//    private Integer version;
//    @ApiModelProperty(value = "创建者")
//    private String creator;
//    @ApiModelProperty(value = "创建者ID")
//    private Long creatorId;
//    @ApiModelProperty(value = "创建时间")
//    private LocalDateTime createdTime;
//    @ApiModelProperty(value = "更新用户")
//    private String lastOperator;
//    @ApiModelProperty(value = "更新用户Id")
//    private Integer lastOperatorId;
//    @ApiModelProperty(value = "更新时间")
//    private String update_Time;
}
