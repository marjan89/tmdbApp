package com.sinisa.bragitask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sinisa.bragitask.di.viewModelModule
import com.sinisa.bragitask.network.di.networkModule
import com.sinisa.bragitask.ui.App
import org.koin.compose.KoinContext
import org.koin.dsl.koinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val koinApp = koinApplication {
            modules(viewModelModule)
            modules(networkModule)
        }
        
        setContent {
            KoinContext(koinApp.koin) {
                App()
            }
        }
    }
}
