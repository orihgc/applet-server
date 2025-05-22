package com.ori.applet.metric

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.DistributionSummary
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.binder.MeterBinder
import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicInteger

@Component
class SalesMetrics : MeterBinder {
    private var orderCount: Counter? = null
    private var totalAmount: Counter? = null
    private var orderSummary: DistributionSummary? = null
    private val averageAmount = AtomicInteger()

    override fun bindTo(registry: MeterRegistry) {
        this.orderCount = registry.counter("order.count", "direction", "income")
        this.totalAmount = registry.counter("order.amount.sum", "direction", "income")
        this.orderSummary = registry.summary("order.summary", "direction", "income")
        registry.gauge("order.amount.average", averageAmount)
    }

    fun makeNewOrder(amount: Int) {
        orderCount?.increment()
        totalAmount?.increment(amount.toDouble())
        orderSummary?.record(amount.toDouble())
        orderSummary?.mean()?.toInt()?.let { averageAmount.set(it) }
    }
}