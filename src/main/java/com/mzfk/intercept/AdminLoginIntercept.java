package com.mzfk.intercept;

import com.alibaba.fastjson.JSON;
import com.mzfk.pojo.AjaxResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author sz
 * @Title: AdminLoginIntercept
 * @ProjectName 8Madmin_config
 * @Description: TODO
 * @date 2018/11/1911:03
 */
public class AdminLoginIntercept implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("=================== Log intercept preHandle!===================");


        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
            throws Exception {


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {


    }


}
