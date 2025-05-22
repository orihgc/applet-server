package com.ori.applet.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


@Component
@ConfigurationProperties("database")
class DataBaseProperties {

     var name: String? = null
     var password: String? = null
}