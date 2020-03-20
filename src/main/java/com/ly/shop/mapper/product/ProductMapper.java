package com.ly.shop.mapper.product;

import com.ly.shop.constant.ShopConstant;
import com.ly.shop.entity.Product;
import com.ly.shop.mapper.product.provider.ProductProvider;
import com.ly.shop.vo.product.ProductAddVo;
import com.ly.shop.vo.product.ProductModifyVo;
import com.sun.deploy.association.utility.AppConstants;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductMapper {


    @Select("select * from app_product where id = #{id} and is_deleted = " + ShopConstant.isDelete.NORMAL)
    @Results(id = "productById", value = {
            @Result(column = "product_name", property = "productName"),
            @Result(column = "product_code", property = "productCode"),
            @Result(column = "main_img", property = "mainImg"),
            @Result(column = "price", property = "price"),
            @Result(column = "origin_price", property = "originPrice"),
            @Result(column = "stock_num", property = "stockNum"),
            @Result(column = "is_deleted", property = "isDeleted"),
            @Result(column = "store_id", property = "storeId"),
            @Result(column = "store_code", property = "storeCode"),
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "creator", property = "creator"),
            @Result(column = "created_time", property = "createdTime"),
            @Result(column = "last_operator", property = "lastOperator"),
            @Result(column = "last_operator_id", property = "lastOperatorId"),
            @Result(column = "update_time", property = "updateTime"),
    })
    Product findById(long id);


    @Update("update  app_product set stock_num = stock_num - #{stockNum} where id =#{id} and stock_num > 0")
    int updateProductStock(long id, int stockNum);


    @Insert("INSERT INTO APP_PRODUCT (product_name,product_code,main_img,price,origin_price,stock_num,store_id,store_code,creator_id," +
            "creator,created_time,last_operator_id,last_operator,update_time,is_deleted) VALUES (#{productName},#{productCode},#{mainImg},#{price},#{originPrice}," +
            "#{stockNum},#{storeId},#{storeCode},#{creatorId},#{creator},#{createdTime},#{lastOperatorId},#{lastOperator},#{updateTime},#{isDeleted})")
    int addProduct(ProductAddVo productAddVo);

    @UpdateProvider(type = ProductProvider.class, method = "updateProduct")
    int updateProduct(ProductModifyVo productAddVo);


    @Select("select * from app_product where  is_deleted = " + ShopConstant.isDelete.NORMAL)
    @Results(value = {
            @Result(column = "product_name", property = "productName"),
            @Result(column = "product_code", property = "productCode"),
            @Result(column = "main_img", property = "mainImg"),
            @Result(column = "price", property = "price"),
            @Result(column = "origin_price", property = "originPrice"),
            @Result(column = "stock_num", property = "stockNum"),
            @Result(column = "is_deleted", property = "isDeleted"),
            @Result(column = "store_id", property = "storeId"),
            @Result(column = "store_code", property = "storeCode"),
            @Result(column = "creator_id", property = "creatorId"),
            @Result(column = "creator", property = "creator"),
            @Result(column = "created_time", property = "createdTime"),
            @Result(column = "last_operator", property = "lastOperator"),
            @Result(column = "last_operator_id", property = "lastOperatorId"),
            @Result(column = "update_time", property = "updateTime"),
    })
    List<Product> findAll();


    @Update("update app_product set is_deleted = " + ShopConstant.isDelete.DELETE + " where id= #{id}")
    public int deleteProduct(long id);
}
