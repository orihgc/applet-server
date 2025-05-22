package com.ori.applet.actuator

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class ActuatorSecurityConfigurer : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        //认证
//        http?.requestMatcher(EndpointRequest.toAnyEndpoint())?.authorizeRequests { it.anyRequest().authenticated() }
        //匿名访问
        http?.requestMatcher(EndpointRequest.toAnyEndpoint())?.authorizeRequests { it.anyRequest().anonymous() }
        http?.httpBasic()
    }
}