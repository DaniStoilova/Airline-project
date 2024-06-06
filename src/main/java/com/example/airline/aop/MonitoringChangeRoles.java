package com.example.airline.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class MonitoringChangeRoles {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringChangeRoles.class);

    @Around("Pointcuts.trackChangeRoles()")
    public Object changeOwnership(ProceedingJoinPoint pjp) throws Throwable {

        Object [] args = pjp.getArgs();

        if (args.length > 0) {
            String email = args[0].toString();
            String role = args[1].toString();

            LOGGER.info("Change role was performed with: {} on {}", role,email);
        } else {
            LOGGER.warn("Invalid method arguments");
        }


        return pjp.proceed();
    }



}
