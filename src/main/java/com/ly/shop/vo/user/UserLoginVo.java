package com.ly.shop.vo.user;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class UserLoginVo implements Serializable {
    @NotBlank(message = "用户名不可以为空")
    private String userName;
    @NotBlank(message = "密码不可以为空")
    private String password;


}
