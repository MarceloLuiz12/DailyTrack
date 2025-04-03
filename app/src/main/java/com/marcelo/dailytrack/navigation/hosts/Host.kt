package com.marcelo.dailytrack.navigation.hosts

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.marcelo.dailytrack.navigation.routes.Routes
import com.marcelo.dailytrack.screens.to_do.ToDoScreen

@Composable
fun Host(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.ToDo
    ) {
        composable<Routes.Progress> {
        }
        composable<Routes.ToDo> {
            ToDoScreen()
        }

        composable<Routes.Done> {

        }
    }
}