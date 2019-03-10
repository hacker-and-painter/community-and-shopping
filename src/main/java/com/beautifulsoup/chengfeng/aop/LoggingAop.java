package com.beautifulsoup.chengfeng.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class LoggingAop {

    private ThreadLocal<Long> startTime=new ThreadLocal<>();

    @Pointcut(value = "execution(public * com.beautifulsoup.chengfeng.controller.community.CommunityController.*(..))")
    public void chengfengLog(){}

    @Before("chengfengLog()")
    public void doBefore(JoinPoint joinPoint)throws Exception{
        startTime.set(System.currentTimeMillis());
    }

    @AfterReturning(returning = "ret",pointcut = "chengfengLog()")
    public void doAfterReturn(Object ret){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.debug(request.getRequestURL().append(" 耗时: ").append(System.currentTimeMillis()-startTime.get()).toString());
    }
}
