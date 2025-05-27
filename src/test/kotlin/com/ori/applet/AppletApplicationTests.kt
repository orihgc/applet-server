package com.ori.applet

import com.zaxxer.hikari.HikariDataSource
import com.zaxxer.hikari.pool.HikariProxyConnection
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import javax.sql.DataSource


@SpringBootTest
class AppletApplicationTests {

    @Autowired
    private val applicationContext: ApplicationContext? = null

    @Test
    fun testDataSource() {
        val dataSource = applicationContext?.getBean("dataSource", DataSource::class.java)
        val connection = dataSource?.connection
        assert(connection is HikariProxyConnection)
    }

    @Test
    fun testMysql(){
        val dataSource = applicationContext?.getBean("dataSource", DataSource::class.java)
        assert(dataSource is HikariDataSource)
        val hikari = dataSource as HikariDataSource

    }

}
