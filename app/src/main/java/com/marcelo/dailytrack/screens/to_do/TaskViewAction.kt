package com.marcelo.dailytrack.screens.to_do

sealed class TaskViewAction {
    data object GetTasks : TaskViewAction()
}