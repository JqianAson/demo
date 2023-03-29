package com.crointech.backstage.common.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义操作日志注解接口类
 * @author Dream
 *
 */
//表示在什么级别保存该注解信息
@Retention(RetentionPolicy.RUNTIME)
//表示该注解用于什么地方
@Target(ElementType.METHOD)
public @interface OperateLog {
    //模块名
    String  operContent();
    //操作类型
    OperType operateType();
}
