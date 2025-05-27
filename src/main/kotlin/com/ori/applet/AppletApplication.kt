package com.ori.applet

import com.ori.applet.aop.SpringHello
import com.ori.applet.beans.Person
import com.ori.applet.data.jdbc.JdbcTmplUserServiceImpl
import com.ori.applet.data.jdbc.SexEnum
import com.ori.applet.data.jdbc.JdbcUser
import com.ori.applet.data.jpa.JpsUserRepository
import com.ori.applet.data.mybatis.MybatisUserService
import com.ori.applet.metric.SalesMetrics
import com.ori.applet.properties.DataBaseProperties
import com.ori.applet.utils.toJson
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.composite.CompositeMeterRegistry
import io.micrometer.core.instrument.logging.LoggingMeterRegistry
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ExitCodeGenerator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.ori.applet"])
@EntityScan(basePackages = ["com.ori.applet"])
@RestController
@EnableAspectJAutoProxy
@EnableScheduling
class AppletApplication {

    companion object {
        var applicationContext: ConfigurableApplicationContext? = null
    }

    @Autowired
    private lateinit var jpaUserRepository: JpsUserRepository

    @Autowired
    private lateinit var mybatisUserService: MybatisUserService

    @RequestMapping("/applet")
    fun hello(): String {
        val person = applicationContext?.getBean(Person::class.java)
        val dataBaseProperties = applicationContext?.getBean(DataBaseProperties::class.java)
        person?.service()
        val springHello = applicationContext?.getBean(SpringHello::class.java)
        return springHello?.sayHello(StringBuffer("hgc")) ?: "Hello"
    }

    @RequestMapping("/user/jdbc")
    fun getUser(): String {
        val userServiceImpl = applicationContext?.getBean(JdbcTmplUserServiceImpl::class.java)
        userServiceImpl?.insertUser(JdbcUser(null, "ori", SexEnum.MALE, "note"))
        return userServiceImpl?.getUser(1).toString()
    }

    @RequestMapping("/user/jpa")
    fun getJpaUser(): String {
        val user = jpaUserRepository.findByUserNameLikeOrNoteLike("ori","note")
        return user.toJson()
    }

    @RequestMapping("/user/mybatis")
    fun getMybatisUser(): String {
        val user = mybatisUserService.findByName(3)
        return user?.toJson()?:""
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

    @Bean
    fun waitExitCodeGenerator(args: ApplicationArguments): ExitCodeGenerator {
        return ExitCodeGenerator {
            if (args.containsOption("wait")) 0 else 1
        }
    }
}

fun main(args: Array<String>) {
    AppletApplication.applicationContext = runApplication<AppletApplication>(*args)
}
