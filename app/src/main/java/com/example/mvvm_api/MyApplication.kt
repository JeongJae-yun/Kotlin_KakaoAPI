package com.example.mvvm_api

import android.app.Application
import com.example.mvvm_api.di.myDiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(myDiModule)
        }
    }
}