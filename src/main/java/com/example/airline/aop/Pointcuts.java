package com.example.airline.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* com.example.airline.service.Impl.UserServiceImpl.changeRole(..))")
    public void trackChangeRoles(){}

}
