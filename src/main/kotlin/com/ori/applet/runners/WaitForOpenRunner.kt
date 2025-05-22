package com.ori.applet.runners

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.util.NumberUtils
import org.springframework.util.StringUtils


@Component
@Order(2)
class WaitForOpenRunner : ApplicationRunner {

    private val log: Logger by lazy { LoggerFactory.getLogger(javaClass) }


    @Throws(Exception::class)
    override fun run(args: ApplicationArguments) {
        val needWait = args.containsOption("wait")
        if (!needWait) {
            log.info("如果没开门，就不用等了。")
            return
        }
        val waitSeconds = args.getOptionValues("wait")
        if (waitSeconds.isNotEmpty()) {
            val seconds = waitSeconds[0].toInt()
            log.info("还没开门，先等{}秒。", seconds)
            Thread.sleep((seconds * 1000).toLong())
        }

        log.info("其他参数：{}", StringUtils.collectionToCommaDelimitedString(args.nonOptionArgs))
    }
}