package com.marcelo.dailytrack.di

import com.marcelo.dailytrack.screens.to_do.TaskViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val  presentationModule = module {
        viewModelOf(::TaskViewModel)
}