package com.ly.shop.mapper.product.provider;

import com.ly.shop.vo.product.ProductAddVo;
import com.ly.shop.vo.product.ProductModifyVo;
import com.ly.shop.vo.user.UserUpdateVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

@Slf4j
public class ProductProvider extends SQL {

    private static final String T_ORDER = "APP_PRODUCT";

    /**
     * 根据条件进行更新
     *
     * @param addVo
     * @return
     */
    public String updateProduct(ProductModifyVo addVo) {
        String userSql = new SQL() {
            {
                UPDATE(T_ORDER);
                if (!StringUtils.isEmpty(addVo.getProductName())) {
                    SET("product_name = #{productName}");
                }
                if (!StringUtils.isEmpty(addVo.getProductCode())) {
                    SET("product_code= #{productCode}");
                }
                if (!StringUtils.isEmpty(addVo.getMainImg())) {
                    SET("main_img = #{mainImg}");
                }
                if (!StringUtils.isEmpty(addVo.getPrice())) {
                    SET("price = #{price}");
                }
                if (!StringUtils.isEmpty(addVo.getOriginPrice())) {
                    SET("origin_price = #{originPrice}");
                }
                if (!StringUtils.isEmpty(addVo.getStockNum())) {
                    SET("stock_num = #{stockNum}");
                }
                if (!StringUtils.isEmpty(addVo.getStoreId())) {
                    SET("store_id = #{storeId}");
                }
                if (!StringUtils.isEmpty(addVo.getStoreCode())) {
                    SET("store_code = #{storeCode}");
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
