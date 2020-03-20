package com.ly.shop.service;


import com.alibaba.fastjson.JSON;
import com.ly.shop.api.ErrCode;
import com.ly.shop.entity.User;
import com.ly.shop.exception.impl.UserIsExistException;
import com.ly.shop.exception.impl.UserNotFoundException;
import com.ly.shop.mapper.user.UserMapper;
import com.ly.shop.vo.user.UserLoginVo;
import com.ly.shop.vo.user.UserRegisterVo;
import com.ly.shop.vo.user.UserSelectVo;
import com.ly.shop.vo.user.UserUpdateVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserMapper userMapper;


    /**
     * @param user
     * @return
     * @see UserRegisterVo
     * 注册
     */
    public int addUserInfo(User user) {
        int userCount = userMapper.countForUserName(user.getUserName(), user.getUserMobile());
        if (userCount > 0) {
            throw new UserIsExistException(ErrCode.USER_IS_EXIST);
        }
        return userMapper.addUser(user);
    }

    /**
     * 查用户
     *
     * @param userSelectVo
     * @return
     */
    public List<User> findUser(UserSelectVo userSelectVo) {
        log.info("findUser---" + userSelectVo.toString());
        List<User> users = userMapper.findUser(userSelectVo);
        if (!users.isEmpty()) {
            return users;
        } else {
            throw new UserNotFoundException();
        }
    }

    /**
     * 更新数据
     *
     * @param userUpdateVo
     * @return
     */
    public int updateUsr(UserUpdateVo userUpdateVo) {
        return userMapper.updateUser(userUpdateVo);
    }

    /**
     * 根据id找用户
     *
     * @param id
     * @return
     */
    public User findById(long id) {
        return userMapper.findById(id);
    }



}
