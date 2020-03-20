package com.ly.shop.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserUpdateVo {
    private static final long serialVersionUID = -2071565876962058344L;
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;
    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "用户密码")
    private String passWord;
    @ApiModelProperty(value = "真实姓名")
    private String readName;
    @ApiModelProperty(value = "用户头像")
    private String userAvatar;
    @ApiModelProperty(value = "手机号码")
    private String userMobile;
    @ApiModelProperty(value = "用户状态：0：正常 1：锁定   2：禁用", required = true)
    private Integer userStatus;
    @ApiModelProperty(value = "微信openId")
    private String openId;
    @ApiModelProperty(value = "用户来源  0：默认  1：企业微信  2：微信  3：QQ", required = true)
    private Integer userSource;
    @ApiModelProperty(value = "用户来源标识，如企业微信成员id/微信openid", required = true)
    private String userSourceIdent;
    @ApiModelProperty(value = "用户所属部门ID")
    private Integer deptId;
    @ApiModelProperty(value = "删除标记0正常1删除")
    private Integer isDeleted;
    @ApiModelProperty(value = "版本号", hidden = true)
    private Integer version;
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
    @ApiModelProperty(value = "历史订单数",hidden = true)
    private Integer hisOrderQuantity;

}
