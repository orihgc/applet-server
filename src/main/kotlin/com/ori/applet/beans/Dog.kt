package com.ori.applet.beans

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
class Dog:Animal {
    override fun use() {
        println("Dog is use")
    }
}