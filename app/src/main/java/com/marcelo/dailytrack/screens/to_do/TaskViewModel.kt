package com.marcelo.dailytrack.screens.to_do

import androidx.lifecycle.viewModelScope
import com.marcelo.dailytrack.base_ui.common.BaseViewModel
import com.marcelo.todokit.domain.usecase.AddTaskUseCase
import com.marcelo.todokit.domain.usecase.GetTasksUseCase
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class TaskViewModel : BaseViewModel<TaskViewAction, TaskViewState, TaskViewNavigation>() {
    private val getAllUseCase: GetTasksUseCase by inject {
        parametersOf(viewModelScope)
    }
    private val addTaskUseCase: AddTaskUseCase by inject {
        parametersOf(viewModelScope)
    }

    override fun initialState() = TaskViewState()

    override fun dispatcherViewAction(action: TaskViewAction) {
        when (action) {
            is TaskViewAction.GetTasks -> getAllTasks()
        }
    }

    private fun getAllTasks() {
        updateState {
            copy(
                isLoading = true
            )
        }
        getAllUseCase(
            onSuccess = {
                updateState {
                    copy(
                        tasks = it,
                        isLoading = false
                    )
                }
            },
            onError = {
                updateState {
                    copy(
                        error = it,
                        isLoading = false
                    )
                }
            }
        )
    }
}