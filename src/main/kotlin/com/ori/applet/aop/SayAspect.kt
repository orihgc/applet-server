package com.ori.applet.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.DeclareParents
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Aspect
@Component
@Order(2)
class SayAspect {

    @DeclareParents(value = "com.ori.applet.aop.SpringHello", defaultImpl = DefaultGoodBye::class)
    private lateinit var bye: GoodBye

    var counter = 0

    @Before("execution(* say*(..)) && args(words)")
    fun countSentence(words: StringBuffer) {
        words.append("[${++counter}]\n")
    }

    @Around("execution(* sayHello(..)) && this(bye)")
    fun addSay(pjp: ProceedingJoinPoint, bye: GoodBye): String {
        return "${pjp.proceed()} ${bye.sayBye()}"
    }
}