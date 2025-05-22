package com.ori.applet.beans

import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.BeanFactoryAware
import org.springframework.beans.factory.BeanNameAware
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Component
class BusinessPerson(@Autowired @Qualifier("dog") val animal: Animal) : Person, BeanNameAware, BeanFactoryAware,
    ApplicationContextAware, InitializingBean, DisposableBean,BeanPostProcessor {

    override fun service() {
        animal.use()
    }

    override fun setBeanName(p0: String) {
        println("setBeanName:$p0")
    }

    override fun setBeanFactory(p0: BeanFactory) {
        println("setBeanFactory")
    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        println("setApplicationContext")
    }

    /**
     * 对所有Bean生效
     * */
    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any? {
        println("postProcessBeforeInitialization")
        return super.postProcessBeforeInitialization(bean, beanName)
    }

    /**
     * 对所有Bean生效
     * */
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
        println("postProcessAfterInitialization")
        return super.postProcessAfterInitialization(bean, beanName)
    }

    @PostConstruct
    fun postConstruct() {
        println("postConstruct")
    }

    @PreDestroy
    fun postDestroy() {
        println("postDestroy")
    }

    override fun afterPropertiesSet() {
        println("afterPropertiesSet")
    }

    override fun destroy() {
        println("destroy")
    }


}