package com.ori.applet.aop

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Aspect
@Component
@Order(1)
class BeforeAspect {


    @Before("target(com.ori.applet.aop.HelloService) && args(words)")
    fun beforeUserPointcut(words:StringBuffer) {
        words.append("Welcome to Spring!")
    }


}