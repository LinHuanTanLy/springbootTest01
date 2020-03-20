package com.ly.shop.service;

import com.ly.shop.entity.Product;
import com.ly.shop.mapper.product.ProductMapper;
import com.ly.shop.vo.product.ProductAddVo;
import com.ly.shop.vo.product.ProductModifyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {


    @Autowired
    ProductMapper productMapper;

    /**
     * 查找某个商品
     *
     * @param id
     * @return
     */
    public Product findOne(Long id) {
        return productMapper.findById(id);
    }

    public int updateStock(long id, int stockNum) {
        return productMapper.updateProductStock(id, stockNum);
    }

    public int addProduct(ProductAddVo productAddVo) {
        return productMapper.addProduct(productAddVo);
    }


    public int modifyProduct(ProductModifyVo modifyVo) {
        return productMapper.updateProduct(modifyVo);
    }
}
