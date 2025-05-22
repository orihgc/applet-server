package com.ori.applet

import com.ori.applet.aop.SpringHello
import com.ori.applet.beans.Person
import com.ori.applet.metric.SalesMetrics
import com.ori.applet.properties.DataBaseProperties
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.composite.CompositeMeterRegistry
import io.micrometer.core.instrument.logging.LoggingMeterRegistry
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sun.java2d.pipe.SpanShapeRenderer.Simple
import kotlin.random.Random

@SpringBootApplication
@RestController
@EnableAspectJAutoProxy
@EnableScheduling
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
        return springHello?.sayHello(StringBuffer("hgc")) ?: "Hello"
    }

    @Autowired
    private val salesMetrics: SalesMetrics? = null

    @Scheduled(fixedRate = 500, initialDelay = 1000)
    fun periodicallyMakeOrder() {
        val random = Random(0)
        salesMetrics?.makeNewOrder(amount = random.nextInt(100))
    }

    @Bean
    fun customMeterRegistry(): MeterRegistry? {
        val compositeMeterRegistry = CompositeMeterRegistry()
        compositeMeterRegistry.add(SimpleMeterRegistry())
        compositeMeterRegistry.add(LoggingMeterRegistry())
        return compositeMeterRegistry
    }
}

fun main(args: Array<String>) {
    AppletApplication.applicationContext = runApplication<AppletApplication>(*args)
}
