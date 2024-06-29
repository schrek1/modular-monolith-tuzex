package cz.schrek.tuzex.common.utils

import cz.schrek.tuzex.common.utils.LoggerUtils.getLoggerForClass
import org.slf4j.MDC
import java.util.concurrent.CancellationException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicLong
import kotlin.time.Duration
import kotlin.time.TimeSource

object ThreadUtils {

    private val logger = getLoggerForClass()

    private val threadId = AtomicLong(1)

    private val executor = Executors.newVirtualThreadPerTaskExecutor()

    fun wait(duration: Duration) = Thread.sleep(duration.inWholeMilliseconds)

    fun  Collection<CompletableFuture<*>>.awaitAll() {
        CompletableFuture.allOf(*this.toTypedArray()).join()
    }

    fun <T> Collection<CompletableFuture<T>>.awaitAll(): List<T> {
        CompletableFuture.allOf(*this.toTypedArray()).join()
        return map { it.getOrThrow() }
    }

    fun <T> CompletableFuture<T>.getOrThrow(): T = try {
        get()
    } catch (e: Exception) {
        throw e
    }

    fun CompletableFuture<*>.abort() = cancel(true)

    fun <U> asyncSupply(
        operation: String = "",
        parentMdc: Map<String, String> = MDC.getCopyOfContextMap() ?: emptyMap(),
        block: () -> U
    ): CompletableFuture<U> = CompletableFuture.supplyAsync({
        setSubThreadMdc(parentMdc, operation)

        val currentThreadId = threadId.getAndIncrement()
        val started = TimeSource.Monotonic.markNow()

        try {
            logger.info("asyncSupply $operation - was started tId=$currentThreadId")
            val result = block()
            logger.info("asyncSupply $operation - was successfully completed after ${started.elapsedNow()} tId=$currentThreadId")
            result
        } catch (e: Exception) {
            e.logAsSubThreadException(operation, currentThreadId, started.elapsedNow())
            throw e
        }
    }, executor)

    fun asyncRun(
        operation: String = "",
        parentMdc: Map<String, String> = MDC.getCopyOfContextMap() ?: emptyMap(),
        block: () -> Unit
    ): CompletableFuture<Void> = CompletableFuture.runAsync({
        setSubThreadMdc(parentMdc, operation)

        val currentThreadId = threadId.getAndIncrement()
        val started = TimeSource.Monotonic.markNow()

        try {
            logger.info("asyncRun $operation - was started tId=$currentThreadId")
            block()
            logger.info("asyncRun $operation - was successfully completed after ${started.elapsedNow()} tId=$currentThreadId")
        } catch (e: Exception) {
            e.logAsSubThreadException(operation, currentThreadId, started.elapsedNow())
            throw e
        }
    }, executor)

    private fun Exception.logAsSubThreadException(
        operation: String,
        currentThreadId: Long,
        elapsedTime: Duration
    ) {
        val messagePrefix = "asyncSupply $operation"
        val messagePostfix = "after $elapsedTime tId=$currentThreadId"

        when (this) {
            is InterruptedException -> logger.warn("$messagePrefix - was interrupted $messagePostfix", this)

            is CancellationException -> logger.info("$messagePrefix - was canceled $messagePostfix")

            else -> logger.warn("$messagePrefix - was failed $messagePostfix", this)
        }
    }

    private fun setSubThreadMdc(parentMdc: Map<String, String>, operation: String) {
        MDC.clear()
        MDC.setContextMap(parentMdc)
        if (operation.isNotBlank()) MDC.put("asyc-operation", operation)
    }
}
