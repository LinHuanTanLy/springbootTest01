package com.ly.shop.entity;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户
 */

@Data
@Getter
@Setter
public class User implements Serializable {
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;
    @ApiModelProperty(value = "用户名称", required = true)
    private String userName;
    @ApiModelProperty(value = "用户密码", required = true)
    private String passWord;
    @ApiModelProperty(value = "真实姓名", required = true)
    private String readName;
    @ApiModelProperty(value = "用户头像", required = true)
    private String userAvatar;
    @ApiModelProperty(value = "手机号码", required = true)
    private String userMobile;
    @ApiModelProperty(value = "用户来源  0：默认  1：企业微信  2：微信  3：QQ", required = true)
    private Integer userSource;
    @ApiModelProperty(value = "用户来源标识，如企业微信成员id/微信openid", required = true)
    private String userSourceIdent;
    @ApiModelProperty(value = "用户状态：0：正常 1：锁定   2：禁用", required = true)
    private Integer userStatus;
    @ApiModelProperty(value = "微信openId")
    private String openId;
    @ApiModelProperty(value = "用户所属部门ID", required = true)
    private Integer deptId;
    @ApiModelProperty(value = "删除标记0正常1删除", required = true)
    private Integer isDeleted;
    @ApiModelProperty(value = "版本号", required = true)
    private Integer version;
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
    private LocalDateTime update_Time;
}
