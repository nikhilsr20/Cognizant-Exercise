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

/**
 * LoggingAspect — AOP Aspect for cross-cutting logging concerns.
 *
 * Exercise 8: Basic AOP with Spring
 *   - Defines pointcuts for the service and controller packages
 *   - @Before  : logs method name and arguments before execution
 *   - @After   : logs method name after execution (always fires)
 *   - @Around  : measures and logs method execution time in ms

 * Spring Boot auto-detects this via @Aspect + @Component since
 * spring-boot-starter-aop adds @EnableAspectJAutoProxy automatically.
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


    // Reusable Pointcut Expressions


    /** All methods in the service package */
    @Pointcut("execution(* com.rishbootdev.librarymanagementsystem.service..*(..))")
    public void serviceMethods() {}

    /** All methods in the controller package */
    @Pointcut("execution(* com.rishbootdev.librarymanagementsystem.controller..*(..))")
    public void controllerMethods() {}

    /** Combined: service OR controller */
    @Pointcut("serviceMethods() || controllerMethods()")
    public void applicationMethods() {}


    // Exercise 8 — @Before Advice

    @Before("applicationMethods()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args     = joinPoint.getArgs();

        logger.info("═══════════════════════════════════════════════");
        logger.info("║ [AOP @Before] Entering : {}", methodName);
        logger.info("║ [AOP @Before] Arguments: {}", Arrays.toString(args));
        logger.info("═══════════════════════════════════════════════");
    }


    // Exercise 8 — @After Advice

    @After("applicationMethods()")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("═════════════════════════════════════════════════");
        logger.info("║ [AOP @After ] Exiting  : {}", methodName);
        logger.info("════════════════════════════════════════════════");
    }


    // Exercise 8 — @Around Advice
    // Wraps each service method to measure execution time

    @Around("serviceMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        long startTime    = System.currentTimeMillis();

        logger.info(">> [AOP @Around] START — {}", methodName);

        Object result;
        try {
            result = joinPoint.proceed();   // Execute the actual method
        } catch (Throwable ex) {
            long elapsed = System.currentTimeMillis() - startTime;
            logger.error(">> [AOP @Around] EXCEPTION in {} after {} ms — {}",
                         methodName, elapsed, ex.getMessage());
            throw ex;
        }

        long executionTime = System.currentTimeMillis() - startTime;
        logger.info(">> [AOP @Around] END   — {} | Execution Time: {} ms",
                    methodName, executionTime);

        return result;
    }
}
