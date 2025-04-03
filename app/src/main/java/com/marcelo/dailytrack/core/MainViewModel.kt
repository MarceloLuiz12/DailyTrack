package com.marcelo.dailytrack.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelo.todokit.domain.entities.Task
import com.marcelo.todokit.domain.enums.TaskStatusEnum
import com.marcelo.todokit.domain.model.TaskModel
import com.marcelo.todokit.domain.usecase.AddTaskUseCase
import com.marcelo.todokit.domain.usecase.GetTasksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class MainViewModel : ViewModel(), KoinComponent {
    private val getAllUseCase: GetTasksUseCase by inject {
        parametersOf(viewModelScope)
    }

    private val addTaskUseCase: AddTaskUseCase by inject {
        parametersOf(viewModelScope)
    }

    private val _tasks = MutableStateFlow<List<TaskModel>>(emptyList())
    val tasks: StateFlow<List<TaskModel>> = _tasks.asStateFlow()
    private val _success = MutableStateFlow<Boolean>(false)
    val success = _success.asStateFlow()


    fun getAllTasks() {
        getAllUseCase(
            onSuccess = { _tasks.value = it },
            onError = {
                println("Error message: ${it.message}")
            }
        )
    }

    fun addTask(
        title: String,
        description: String,
        status: TaskStatusEnum
    ) {
        addTaskUseCase(
            params = Task(
                id = 1L,
                title = title,
                description = description,
                status = status.value,
                createdAt = System.currentTimeMillis()
            ),
            onSuccess = {
                _success.value = true
                println("Task added successfully")
            },
            onError = {
                _success.value = false
                println("Error message: ${it.message}")
            }
        )
    }
}
