package com.mzfk.intercept;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sz
 * @Title: UnCheckedFilter
 * @ProjectName 8Madmin_config
 * @Description: 此注解的作用是禁止方法拦截（禁止session登录验证）
 * @date 2018/10/1910:54
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UnCheckedFilter {

}