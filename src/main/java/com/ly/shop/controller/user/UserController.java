package com.ly.shop.controller.user;


import com.ly.shop.api.CommResult;
import com.ly.shop.api.ErrCode;
import com.ly.shop.entity.User;
import com.ly.shop.exception.impl.UserNotFoundException;
import com.ly.shop.service.UserService;
import com.ly.shop.vo.user.UserLoginVo;
import com.ly.shop.vo.user.UserRegisterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@Validated
@Api(value = "用户管理")
@RequestMapping("api/user/")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public CommResult<User> postLogin(@Validated @RequestBody UserLoginVo userLoginVo) {
        User user = userService.findByUserLoginVo(userLoginVo);
        return CommResult.suc(user);
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    public CommResult<UserRegisterVo> postRegister(@Validated @RequestBody UserRegisterVo userRegisterVo) {
        int resultRow = userService.addUserInfo(userRegisterVo);
        if (resultRow == 1) {
            return CommResult.suc(userRegisterVo);
        } else {
            return CommResult.fail(ErrCode.REQUEST_ERR.getCode(), "注册失败");
        }
    }


}
