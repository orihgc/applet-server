package com.ori.applet.actuator

import com.ori.applet.properties.DataBaseProperties
import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.actuate.health.AbstractHealthIndicator
import org.springframework.boot.actuate.health.Health
import org.springframework.stereotype.Component


@Component
class ShopReadyHealthIndicator(binaryTeaPropertiesProvider: ObjectProvider<DataBaseProperties?>) : AbstractHealthIndicator() {

    private val binaryTeaProperties: DataBaseProperties? = binaryTeaPropertiesProvider.getIfAvailable()

    @Throws(Exception::class)
    override fun doHealthCheck(builder: Health.Builder) {
        if (binaryTeaProperties == null || binaryTeaProperties.name.isNullOrBlank()) {
            builder.down()
        } else {
            builder.up()
        }
    }
}