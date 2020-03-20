package com.ly.shop.mapper.order.provider;

import com.ly.shop.constant.ShopConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

@Slf4j
public class OrderProvider extends SQL {

    private static final String T_ORDER = "APP_ORDER";

    public static String selectOrder(Long userId, String status) {
        String userSql = new SQL() {
            {
                SELECT("*");
                FROM(T_ORDER);
                if (userId != null) {
                    WHERE("user_id = #{userId}");
                }
                if (!StringUtils.isEmpty(status)) {
                    WHERE("status = #{status}");
                }
                WHERE("is_deleted = " + ShopConstant.isDelete.NORMAL);
            }
        }.toString();
        log.info("in the method ,the sql is " + userSql);
        log.info("in the method ,the userId is " + userId);
        log.info("in the method ,the status is " + status);
        return userSql;
    }
}
