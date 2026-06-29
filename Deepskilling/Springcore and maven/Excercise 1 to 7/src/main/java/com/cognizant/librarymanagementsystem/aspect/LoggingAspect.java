package com.cognizant.librarymanagementsystem.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * LoggingAspect - AOP Aspect for cross-cutting logging concerns.
 *
 * Exercise 3: Logging with Spring AOP
 *   - Intercepts all methods in the service and repository packages
 *   - Logs method execution times using @Around advice
 *   - Registered in applicationContext.xml via <aop:aspectj-autoproxy/>
 *
 * Exercise 6: @Aspect + @Component allow Spring to detect this via
 *             component scanning (no XML bean definition needed for it)
 */
@Aspect    // Marks this class as an AOP Aspect
@Component // Registers it as a Spring bean (picked up by component scan)
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // ---------------------------------------------------------------
    // Exercise 3: @Around advice — tracks execution time of every
    // method in the service and repository packages
    // ---------------------------------------------------------------
    @Around("execution(* com.rishbootdev.librarymanagementsystem.service..*(..))" +
            " || execution(* com.rishbootdev.librarymanagementsystem.repository..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName  = joinPoint.getSignature().toShortString();
        long startTime     = System.currentTimeMillis();

        logger.info("----------------------------------------------------------");
        logger.info("[AOP - AROUND] BEFORE >> Executing: {}", methodName);

        Object result;
        try {
            result = joinPoint.proceed();   // Execute the actual method
        } catch (Throwable ex) {
            logger.error("[AOP - AROUND] Exception in {}: {}", methodName, ex.getMessage());
            throw ex;
        }

        long executionTime = System.currentTimeMillis() - startTime;
        logger.info("[AOP - AROUND] AFTER  >> Completed: {} | Execution Time: {} ms", methodName, executionTime);
        logger.info("----------------------------------------------------------");

        return result;
    }

    // ---------------------------------------------------------------
    // @Before advice — fires just before service methods execute
    // ---------------------------------------------------------------
    @Before("execution(* com.rishbootdev.librarymanagementsystem.service..*(..))")
    public void logBeforeServiceMethod(org.aspectj.lang.JoinPoint joinPoint) {
        logger.info("[AOP - BEFORE] Entering service method: {}",
                    joinPoint.getSignature().getName());
    }

    // ---------------------------------------------------------------
    // @After advice — fires after service methods complete (always)
    // ---------------------------------------------------------------
    @After("execution(* com.rishbootdev.librarymanagementsystem.service..*(..))")
    public void logAfterServiceMethod(org.aspectj.lang.JoinPoint joinPoint) {
        logger.info("[AOP - AFTER ] Exiting  service method: {}",
                    joinPoint.getSignature().getName());
    }
}
