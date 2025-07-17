package com.interview.patient_service.config
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration
class AsyncConfig {
    @Bean("taskExecutor")
    fun taskExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 2
        executor.maxPoolSize = 10
        executor.setQueueCapacity(500)
        executor.setThreadNamePrefix("Async-")
        executor.initialize()
        println("▶ taskExecutor registered: $executor")
        return executor
    }
}