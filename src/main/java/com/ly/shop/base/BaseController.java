package com.ly.shop.base;

import com.ly.shop.constant.ShopConstant;
import com.ly.shop.entity.User;
import com.sun.deploy.association.utility.AppConstants;

import javax.servlet.http.HttpSession;

public class BaseController {


    public User getUserFromSession(HttpSession httpSession) {
        if (httpSession != null) {
            Object o = httpSession.getAttribute(ShopConstant.SESSION_KEY_NAME);
            if (o != null) {
                return (User) o;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
