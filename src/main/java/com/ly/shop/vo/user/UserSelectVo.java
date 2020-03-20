package com.ly.shop.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查找专用
 */
@Data
@NoArgsConstructor
public class UserSelectVo {
    public UserSelectVo(Long id) {
        this.id = id;
    }

    public UserSelectVo(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    @ApiModelProperty(value = "主键ID")
    private Long id;
    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "用户密码")
    private String passWord;
    @ApiModelProperty(value = "真实姓名")
    private String readName;
    @ApiModelProperty(value = "手机号码")
    private String userMobile;
}
