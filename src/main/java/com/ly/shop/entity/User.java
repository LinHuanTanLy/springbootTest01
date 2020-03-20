package com.ly.shop.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户
 */

@Data
public class User implements Serializable {
    private static final long serialVersionUID = -2071565876962058344L;
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
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "历史订单数")
    private Integer hisOrderQuantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getReadName() {
        return readName;
    }

    public void setReadName(String readName) {
        this.readName = readName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Integer getUserSource() {
        return userSource;
    }

    public void setUserSource(Integer userSource) {
        this.userSource = userSource;
    }

    public String getUserSourceIdent() {
        return userSourceIdent;
    }

    public void setUserSourceIdent(String userSourceIdent) {
        this.userSourceIdent = userSourceIdent;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getLastOperator() {
        return lastOperator;
    }

    public void setLastOperator(String lastOperator) {
        this.lastOperator = lastOperator;
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
