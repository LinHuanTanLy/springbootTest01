package com.ly.shop.mapper.user;


import com.ly.shop.entity.User;
import com.ly.shop.vo.user.UserLoginVo;
import com.ly.shop.vo.user.UserRegisterVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

/**
 * 用户的mapper
 */
public interface UserMapper {


    @Select("SELECT * FROM APP_USER WHERE user_name =  #{username} and password =  #{password}")
    public User findByUserNamePassWord(UserLoginVo userLoginVo);

    @Insert("INSERT INTO APP_USER (user_name,password,user_source,user_source_ident) values (#{userName},#{passWord},#{userSource},#{userSourceIdent})")
    public int addUser(User user);

    @Select("SELECT COUNT(*) FROM APP_USER WHERE user_name =  #{userName}")
    public int countForUserName(String userName);
}
