package com.sinisa.bragitask.network.tmdb

import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.request.parameter
import io.ktor.util.AttributeKey

class TmdbAuthInterceptor {
    companion object Plugin : HttpClientPlugin<Config, TmdbAuthInterceptor> {

        const val API_KEY = "api_key"
        const val ATTRIBUTE_KEY_NAME = "TmdbAuthInterceptor"

        override val key = AttributeKey<TmdbAuthInterceptor>(ATTRIBUTE_KEY_NAME)
        
        override fun prepare(block: Config.() -> Unit): TmdbAuthInterceptor {
            return TmdbAuthInterceptor()
        }
        
        override fun install(plugin: TmdbAuthInterceptor, scope: HttpClient) {
            scope.requestPipeline.intercept(HttpRequestPipeline.Before) {
                context.parameter(API_KEY, TmdbApiConfig.API_KEY)
            }
        }
    }
    
    class Config
}