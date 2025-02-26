package com.zgxt.demo.trigger.aop;


import com.alibaba.fastjson.JSON;
import com.zgxt.demo.app.dto.RequestCmd;
import com.zgxt.demo.common.Result;
import com.zgxt.demo.domain.entity.RoleEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;


@Aspect
@Component
public class ServiceAuth {


    @Pointcut("@annotation(com.zgxt.demo.trigger.aop.AuthPermisson)")
    public void AuthPermisson(){}


    @Around("AuthPermisson()")
    public Object around(ProceedingJoinPoint pjp ) throws Throwable {
        List<RequestCmd> requestCmds = JSON.parseArray(JSON.toJSONString(pjp.getArgs()), RequestCmd.class);
        RoleEntity roleEntity = RoleEntity.builder()
                .user(requestCmds.get(0).getUser())
                .build().init();
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        if (method.getAnnotation(AuthPermisson.class).value()
                .equals(roleEntity.getRoleModel())) return pjp.proceed();
        return Result.permissionError();
    }
}

