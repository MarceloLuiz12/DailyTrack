package com.marcelo.dailytrack.screens.to_do

import com.marcelo.dailytrack.base_ui.common.BaseViewState
import com.marcelo.todokit.domain.model.TaskModel

data class TaskViewState(
    override val isLoading: Boolean = false,
    override val error: Throwable? = null,
    val  tasks: List<TaskModel> = listOf()
): BaseViewState(
    isLoading =  isLoading,
    error = error
)