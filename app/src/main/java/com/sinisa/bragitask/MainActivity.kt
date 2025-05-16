package com.sinisa.bragitask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sinisa.bragitask.di.coroutineModule
import com.sinisa.bragitask.di.repositoryModule
import com.sinisa.bragitask.di.viewModelModule
import com.sinisa.bragitask.network.di.networkModule
import org.koin.compose.KoinContext
import org.koin.dsl.koinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val koinApp = koinApplication {
            modules(viewModelModule)
            modules(networkModule)
            modules(repositoryModule)
            modules(coroutineModule)
        }
        
        setContent {
            KoinContext(koinApp.koin) {
                App()
            }
        }
    }
}
