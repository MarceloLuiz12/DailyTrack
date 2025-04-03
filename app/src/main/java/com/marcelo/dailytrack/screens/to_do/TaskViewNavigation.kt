package com.marcelo.dailytrack.screens.to_do

sealed class TaskViewNavigation {
    data object GoToDetails : TaskViewNavigation()
}