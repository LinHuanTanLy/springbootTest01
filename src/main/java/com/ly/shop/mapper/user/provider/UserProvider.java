package com.ly.shop.mapper.user.provider;

import com.ly.shop.constant.ShopConstant;
import com.ly.shop.vo.user.UserSelectVo;
import com.ly.shop.vo.user.UserUpdateVo;
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
                WHERE("user_status =" + ShopConstant.userStatus.NORMAL);
                WHERE("is_deleted = " + ShopConstant.isDelete.NORMAL);
            }
        }.toString();
        log.info("in the method ,the sql is " + userSql);
        return userSql;
    }

    /**
     * 根据条件进行更新
     *
     * @param userUpdateVo
     * @return
     */
    public String updateUser(UserUpdateVo userUpdateVo) {
        String userSql = new SQL() {
            {
                UPDATE(T_USER);
                if (!StringUtils.isEmpty(userUpdateVo.getUserName())) {
                    SET("user_name = #{userName}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getPassWord())) {
                    SET("password= #{passWord}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getReadName())) {
                    SET("real_name = #{readName}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getUserAvatar())) {
                    SET("user_avatar = #{userAvatar}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getUserMobile())) {
                    SET("user_mobile = #{userMobile}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getUserStatus())) {
                    SET("user_status = #{userStatus}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getOpenId())) {
                    SET("open_id = #{openId}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getDeptId())) {
                    SET("dept_id = #{deptId}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getUserSource())) {
                    SET("user_source = #{userSource}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getUserSourceIdent())) {
                    SET("user_source_ident = #{userSourceIdent}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getIsDeleted())) {
                    SET("is_deleted = #{isDeleted}");
                }
                if (!StringUtils.isEmpty(userUpdateVo.getHisOrderQuantity())) {
                    SET("his_order_quantity = #{hisOrderQuantity}");
                }
                SET("update_time =  #{updateTime}");
                SET("last_operator_id =  #{lastOperatorId}");
                SET("last_operator =  #{lastOperator}");
                WHERE("id = #{id}");
            }
        }.toString();
        log.info("in the method ,the sql is " + userSql);
        return userSql;
    }
}
