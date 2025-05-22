package com.ori.applet.aop

import org.springframework.stereotype.Component

@Component
class SpringHello:HelloService {
    override fun sayHello(words: StringBuffer): String {
        return "Hello! $words"
    }
}