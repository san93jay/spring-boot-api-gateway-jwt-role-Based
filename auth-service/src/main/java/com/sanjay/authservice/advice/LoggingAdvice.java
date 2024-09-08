package com.sanjay.authservice.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {
    Logger log= LoggerFactory.getLogger(LoggingAdvice.class);

    @Around("myPointCut()")
    public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper obj=new ObjectMapper();
        String methodName=proceedingJoinPoint.getSignature().getName();
        String className=proceedingJoinPoint.getTarget().getClass().toString();
        Object[] input=proceedingJoinPoint.getArgs();
        log.info("service start :"+ className +" Method :"+ methodName+" input :"+ obj.writeValueAsString(input));
        Object output =proceedingJoinPoint.proceed();
        log.info("service end :"+ className +" Method :"+ methodName+" output :"+ obj.writeValueAsString(output));
        return output;
    }

    @Pointcut("execution(public * com.sanjay.authservice.*.*.*(..))")
    public void myPointCut(){

    }
}
