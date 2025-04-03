package com.marcelo.dailytrack.navigation.routes

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object ToDo : Routes()

    @Serializable
    data object  Progress : Routes()

    @Serializable
    data object Done : Routes()
}