package com.ori.applet

import com.ori.applet.aop.SpringHello
import com.ori.applet.beans.Person
import com.ori.applet.properties.DataBaseProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
@EnableAspectJAutoProxy
class AppletApplication {

    companion object {
        var applicationContext: ConfigurableApplicationContext? = null
    }

    @RequestMapping("/applet")
    fun hello(): String {
        val person = applicationContext?.getBean(Person::class.java)
        val dataBaseProperties = applicationContext?.getBean(DataBaseProperties::class.java)
        person?.service()
        val springHello = applicationContext?.getBean(SpringHello::class.java)
        return springHello?.sayHello(StringBuffer("hgc"))?:"Hello"
    }
}

fun main(args: Array<String>) {
    AppletApplication.applicationContext = runApplication<AppletApplication>(*args)
}
