package com.marcelo.dailytrack.core

import android.app.Application
import com.marcelo.todokit.di.dataLocalModule
import com.marcelo.todokit.di.dataModule
import com.marcelo.todokit.di.domainModule
import com.marcelo.todokit.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

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
                appModule
            )
        }
    }
}

val appModule  = module {
    viewModel { MainViewModel() }
}