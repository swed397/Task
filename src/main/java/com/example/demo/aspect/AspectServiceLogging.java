package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AspectServiceLogging {

    @Before(value = "execution(public * com.example.demo.services.*.find*(..))")
    public void findMethodServiceLogging(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        String fullName = methodSignature.getDeclaringTypeName();
        String className = fullName.substring(fullName.lastIndexOf(".") + 1);

        log.info("From AOP. Service " + className + "." + methodName + " method running");
    }

    @Around("execution(public * com.example.demo.services.*.find*(..))")
    public Object methodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        String fullName = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String className = fullName.substring(fullName.lastIndexOf(".") + 1);

        log.info("From AOP. Service " + className + "." + methodName + " method started");
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        log.info("From AOP. Service " + className + "." + methodName + " method ended. Duration: " + duration);

        return out;
    }
}
