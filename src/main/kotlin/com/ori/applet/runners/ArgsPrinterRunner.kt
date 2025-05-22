package com.ori.applet.runners

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils


@Component
@Order(1)
class ArgsPrinterRunner : CommandLineRunner {

    private val log: Logger by lazy { LoggerFactory.getLogger(javaClass) }

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        log.info("共传入了{}个参数。分别是：{}", args.size, StringUtils.arrayToCommaDelimitedString(args))
    }
}