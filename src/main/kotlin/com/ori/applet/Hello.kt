package com.ori.applet

import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Component
class Hello {

    @Value("hgc")
    var name: String? = null

    fun hello() = "Hello World! by $name"


}