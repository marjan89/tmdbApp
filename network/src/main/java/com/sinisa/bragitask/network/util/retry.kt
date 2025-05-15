package com.sinisa.bragitask.network.util

import com.sinisa.bragitask.network.NetworkConfig
import kotlinx.coroutines.delay

suspend inline fun <T> T.retry(
    block: T.() -> Unit,
    errorBlock: T.(Throwable) -> Unit
) {
    val maxRetryCount = NetworkConfig.Retry.MAX_RETRIES
    repeat(maxRetryCount) { retryCount ->
        runCatching {
            block()
        }.onSuccess {
            return
        }.onFailure {
            if (retryCount < maxRetryCount - 1) delay(NetworkConfig.Retry.RETRY_DELAY)
            else errorBlock(it)
        }
    }
}
