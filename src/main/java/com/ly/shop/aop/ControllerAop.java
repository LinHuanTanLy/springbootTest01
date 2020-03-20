package com.ly.shop.aop;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.ly.shop.api.ErrCode;
import com.ly.shop.exception.impl.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import static com.ly.shop.constant.ShopConstant.SESSION_KEY_NAME;

@Aspect  //使之成为切面类
@Component //把切面类加入到IOC容器中
@Slf4j
public class ControllerAop {
    /**
     * 排除方法名
     * 很恶心的做法
     */
    private List<String> excludeMethodList = new ArrayList<>();

    public ControllerAop() {
        excludeMethodList.add("postLogin");
        excludeMethodList.add("postRegister");
        excludeMethodList.add("getSession");
    }

    /**
     * 1、execution(): 表达式主体。
     * <p>
     * 2、第一个*号：方法返回类型， *号表示所有的类型。
     * <p>
     * 3、包名：表示需要拦截的包名。
     * <p>
     * 4、第二个*号：表示类名，*号表示所有的类。
     * <p>
     * 5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面( )里面表示方法的参数，两个句点表示任何参数
     */
    @Pointcut(value = "execution(public * com.ly.shop.controller..*.*(..)))")
    public void executeService() {
    }

    /*
     *  01 . 前置通知：方法调用前被调用
     */
    @Before("executeService()")
    public void doBeforeAdvice(JoinPoint joinPoint) {//  通过JoinPoint 获取通知的签名信息，如目标方法名，目标方法参数信息等
        System.out.println("我是前置通知");
        Object[] obj = joinPoint.getArgs();//获取目标方法的参数信息
        joinPoint.getThis(); // AOP代理类信息
        joinPoint.getTarget(); // 代理的目标对象
        Signature signature = joinPoint.getSignature(); //  用的最多，通知的签名
        System.out.println("代理的方法是 ： " + signature.getName()); //  打印 代理的是哪一个方法
        // AOP 代理的名字
        System.out.println("AOP 代理的名字 ： " + signature.getDeclaringTypeName());
        signature.getDeclaringType();//  AOP代理类的类（class）信息

        /*
          通过RequestContextHolder获取请求信息，如session 信息 ;
         */
        //  获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //  从requestAttributes中获取HttpServletRequest信息
        assert requestAttributes != null;
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //  获取session信息
        HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);

        System.out.println("请求 ： " + request + " ,  HttpSession : " + session);
        if (!excludeMethodList.contains(signature.getName()) && session != null) {
            Object o = session.getAttribute(SESSION_KEY_NAME);
            if (o == null) {
                throw new UserNotFoundException(ErrCode.UNAUTHORIZED);
            }
        }


        if (request != null) {
            Enumeration<String> enumerations = request.getParameterNames();
            Map<String, String> parameterMaps = Maps.newHashMap();
            while (enumerations.hasMoreElements()) {
                String parameter = enumerations.nextElement();
                parameterMaps.put(parameter, request.getParameter(parameter));
            }
            String str = JSON.toJSONString(parameterMaps);
            if (obj.length > 0) {
                System.out.println("请求参数信息为 ： " + str);
            }
        }
    }

    /**
     * 02  .后置返回通知
     * 需要注意：
     * 如果第一个参数是JoinPoint，则第二个参数是返回值的信息
     * 如果参数中的第一个不是JoinPoint，则第一个参数是returning中对应的参数，
     * returning 限定了只有目标方法返回值与通知方法相应参数类型时才能
     * 执行后置返回通知，否则不执行;
     * 对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     *
     * @param joinPoint
     * @param keys
     */
    @AfterReturning(value = "execution(* com.ly.shop.controller..*.*(..))", returning = "keys")
    public void doAfterReturningAdvice1(JoinPoint joinPoint, Object keys) {
        System.out.println("后置通知执行了！！");
        System.out.println("第一个后置返回通知的返回值是 ：" + JSON.toJSONString(keys));
    }

    @AfterReturning(value = "execution(*  com.ly.shop.controller..*.*(..))", returning = "keys", argNames = "keys")
    public void doAfterReturningAdvice2(String keys) { // 通知方法形影参数的类型是String
        System.out.println("第二个后置返回通知的返回值是 ：" + JSON.toJSONString(keys));
    }
}
