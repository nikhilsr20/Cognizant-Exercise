package com.cognizant.librarymanagementsystem.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.cognizant.librarymanagementsystem.service..*(..))")
    public void serviceMethods() {}

    @Pointcut("execution(* com.cognizant.librarymanagementsystem.controller..*(..))")
    public void controllerMethods() {}

    @Pointcut("serviceMethods() || controllerMethods()")
    public void applicationMethods() {}

    // Exercise 8 – @Before Advice
    @Before("applicationMethods()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        logger.info("═══════════════════════════════════════════════");
        logger.info("║ [AOP @Before] Entering : {}", methodName);
        logger.info("║ [AOP @Before] Arguments: {}", Arrays.toString(args));
        logger.info("═══════════════════════════════════════════════");
    }

    // Exercise 8 – @After Advice
    @After("applicationMethods()")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();

        logger.info("═══════════════════════════════════════════════");
        logger.info("║ [AOP @After] Exiting : {}", methodName);
        logger.info("═══════════════════════════════════════════════");
    }

    // Exercise 8 – @Around Advice
    @Around("serviceMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        long startTime = System.currentTimeMillis();

        logger.info(">> [AOP @Around] START - {}", methodName);

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable ex) {
            long elapsed = System.currentTimeMillis() - startTime;
            logger.error(">> [AOP @Around] EXCEPTION in {} after {} ms - {}",
                    methodName, elapsed, ex.getMessage());
            throw ex;
        }

        long executionTime = System.currentTimeMillis() - startTime;
        logger.info(">> [AOP @Around] END - {} | Execution Time: {} ms",
                methodName, executionTime);

        return result;
    }
}