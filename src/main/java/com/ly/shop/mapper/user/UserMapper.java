package com.ly.shop.mapper.user;


import com.ly.shop.constant.ShopConstant;
import com.ly.shop.entity.User;
import com.ly.shop.mapper.user.provider.UserProvider;
import com.ly.shop.vo.user.UserLoginVo;
import com.ly.shop.vo.user.UserSelectVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户的mapper
 */
public interface UserMapper {
//    Result[] result = new Result[]{};


//    @Select("SELECT * FROM APP_USER WHERE user_name = #{userName}")
//    @Results(id = "userByName", value = {
//            @Result(column = "user_name", property = "userName"),
//            @Result(column = "real_name", property = "readName"),
//            @Result(column = "user_avatar", property = "userAvatar"),
//            @Result(column = "user_mobile", property = "userMobile"),
//            @Result(column = "user_source", property = "userSource"),
//            @Result(column = "user_source_ident", property = "userSourceIdent"),
//            @Result(column = "user_status", property = "userStatus"),
//            @Result(column = "open_id", property = "openId"),
//            @Result(column = "dept_id", property = "deptId"),
//            @Result(column = "is_deleted", property = "isDeleted"),
//            @Result(column = "creator_id", property = "creatorId"),
//            @Result(column = "created_time", property = "createdTime"),
//            @Result(column = "last_operator", property = "lastOperator"),
//            @Result(column = "last_operator_id", property = "lastOperatorId"),
//            @Result(column = "update_time", property = "updateTime"),
//    })
//    User findByUserName(String userName);

//    @Select("SELECT * FROM APP_USER WHERE user_name =  #{userName} and password =  #{passWord}")
//    @Results(id = "user", value = {
//            @Result(column = "user_name", property = "userName"),
//            @Result(column = "real_name", property = "readName"),
//            @Result(column = "user_avatar", property = "userAvatar"),
//            @Result(column = "user_mobile", property = "userMobile"),
//            @Result(column = "user_source", property = "userSource"),
//            @Result(column = "user_source_ident", property = "userSourceIdent"),
//            @Result(column = "user_status", property = "userStatus"),
//            @Result(column = "open_id", property = "openId"),
//            @Result(column = "dept_id", property = "deptId"),
//            @Result(column = "is_deleted", property = "isDeleted"),
//            @Result(column = "creator_id", property = "creatorId"),
//            @Result(column = "created_time", property = "createdTime"),
//            @Result(column = "last_operator", property = "lastOperator"),
//            @Result(column = "last_operator_id", property = "lastOperatorId"),
//            @Result(column = "update_time", property = "updateTime"),
//    })
//    User findByUserNamePassWord(UserLoginVo userLoginVo);

    @Insert("INSERT INTO APP_USER (user_name,password,user_source,user_source_ident,user_mobile) values (#{userName},#{passWord},#{userSource},#{userSourceIdent},#{userMobile})")
    int addUser(User user);

    @Select("SELECT COUNT(*) FROM APP_USER WHERE user_name =  #{userName} or user_mobile = #{mobile}")
    int countForUserName(String userName, String mobile);


    @SelectProvider(type = UserProvider.class, method = "selectUser")
    @Results(id = "userById", value = {
            @Result(column = "user_name", property = "userName"),
            @Result(column = "real_name", property = "readName"),
            @Result(column = "user_avatar", property = "userAvatar"),
            @Result(column = "user_mobile", property = "userMobile"),
            @Result(column = "user_source", property = "userSource"),
            @Result(column = "user_source_ident", property = "userSourceIdent"),
            @Result(column = "user_status", property = "userStatus"),
            @Result(column = "open_id", property = "openId"),
            @Result(column = "dept_id", property = "deptId"),
            @Result(column = "is_deleted", property = "isDeleted"),
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "created_time", property = "createdTime"),
            @Result(column = "last_operator", property = "lastOperator"),
            @Result(column = "last_operator_id", property = "lastOperatorId"),
            @Result(column = "update_time", property = "updateTime"),
    })
    List<User> findUser(UserSelectVo userSelectVo);

}
