package com.example.animequotesproject

import android.app.Application
import com.example.animequotesproject.di.appModule
import com.example.animequotesproject.di.dataModule
import com.example.animequotesproject.di.domainModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(
                appModule,
                domainModule,
                dataModule
            ))
        }
    }

}