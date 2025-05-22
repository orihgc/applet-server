package com.ori.applet.actuator

import com.ori.applet.properties.DataBaseProperties
import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.actuate.endpoint.annotation.Endpoint
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation
import org.springframework.stereotype.Component


@Component
@Endpoint(id = "shop")
class ShopEndpoint(binaryTeaProperties: ObjectProvider<DataBaseProperties?>) {
    private val binaryTeaProperties: DataBaseProperties? = binaryTeaProperties.getIfAvailable()

    @ReadOperation
    fun state(): String {
        return if (binaryTeaProperties == null || binaryTeaProperties.name.isNullOrBlank()) {
            "We're not ready."
        } else {
            "We open ${binaryTeaProperties.name}."
        }
    }
}