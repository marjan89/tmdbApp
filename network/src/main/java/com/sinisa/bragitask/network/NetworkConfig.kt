package com.sinisa.bragitask.network

object NetworkConfig {

    object Retry {
        const val MAX_RETRIES = 3
        const val RETRY_DELAY = 10_000L
    }

    object Timeout {
        const val REQUEST = 30L
        const val CONNECT = 30L
        const val SOCKET = 30L
    }
}