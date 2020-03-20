package com.ly.shop.mapper.user.provider;

import com.ly.shop.constant.ShopConstant;
import com.ly.shop.vo.user.UserSelectVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

/**
 * user专用sql
 */
@Slf4j
public class UserProvider extends SQL {

    private static final String T_USER = "APP_USER";

    /**
     * 根据条件进行查询
     *
     * @param selectVo
     * @return
     */
    public String selectUser(UserSelectVo selectVo) {

        String userSql = new SQL() {
            {
                SELECT("*");
                FROM(T_USER);
                if (selectVo.getId() != null) {
                    WHERE("id = #{id}");
                }
                if (!StringUtils.isEmpty(selectVo.getUserName())) {
                    WHERE("user_name = #{userName}");
                }
                if (!StringUtils.isEmpty(selectVo.getPassWord())) {
                    WHERE("password = #{passWord}");
                }
                if (!StringUtils.isEmpty(selectVo.getReadName())) {
                    WHERE("real_name = #{readName}");
                }
                if (!StringUtils.isEmpty(selectVo.getUserMobile())) {
                    WHERE("user_mobile = #{userMobile}");
                }
                WHERE("user_status =" + ShopConstant.isDeleteStatus.NORMAL);
            }
        }.toString();


        log.info("in the method ,the sql is " + userSql);
        return userSql;
    }
}
