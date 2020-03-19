package com.ly.shop.service;


import com.ly.shop.api.ErrCode;
import com.ly.shop.entity.User;
import com.ly.shop.exception.impl.UserIsExistException;
import com.ly.shop.exception.impl.UserNotFoundException;
import com.ly.shop.mapper.user.UserMapper;
import com.ly.shop.vo.user.UserLoginVo;
import com.ly.shop.vo.user.UserRegisterVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserMapper userMapper;


    public User findByUserLoginVo(UserLoginVo userLoginVo) {
        // 理论上前端传过来的应该是加密后的pw，但是前端目前用postman请求
        // 在service模拟加密
        String passWord = userLoginVo.getPassword();
        // 加密
        userLoginVo.setPassword(passWord);
        User user = userMapper.findByUserNamePassWord(userLoginVo);
        if (user == null) {
            throw new UserNotFoundException(ErrCode.USER_NOT_EXIST);
        }
        return user;
    }


    public int addUserInfo(UserRegisterVo registerVo) {
        int userName = userMapper.countForUserName(registerVo.getUserName());
        if (userName > 0) {
            throw new UserIsExistException(ErrCode.USER_IS_EXIST);
        }
        log.info("userLoginVo is " + registerVo.toString());
        User user = new User();
        BeanUtils.copyProperties(registerVo, user);
        log.info("after copy, the user  is " + user.toString());
        /// 补上空缺的字段
        /// 创建者可以从 Authentication  中拿到
        user.setCreatedTime(LocalDateTime.now());
        user.setUpdate_Time(LocalDateTime.now());
        user.setVersion(1);

        return userMapper.addUser(user);

    }
}
