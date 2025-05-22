package com.ori.applet.beans

import org.springframework.stereotype.Component

@Component
class Cat:Animal {
    override fun use() {
        println("cat is use")
    }
}