package com.marcelo.dailytrack.core

import android.app.Application
import com.marcelo.dailytrack.di.presentationModule
import com.marcelo.todokit.di.dataLocalModule
import com.marcelo.todokit.di.dataModule
import com.marcelo.todokit.di.domainModule
import com.marcelo.todokit.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(
                dataModule,
                domainModule,
                dataLocalModule,
                platformModule,
                presentationModule
            )
        }
    }
}