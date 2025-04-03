package com.marcelo.dailytrack.base_ui.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

abstract class BaseViewModel<A, V : Any, N : Any> : ViewModel(), KoinComponent {

    private val _viewState = MutableStateFlow(this.initialState())
    val viewState get() = _viewState.asStateFlow()

    private val _navigation = MutableStateFlow<N?>(null)
    val navigation = _navigation.asStateFlow()

    abstract fun initialState(): V

    fun updateState(update: V.() -> V) {
        _viewState.value = _viewState.value.update()
    }

    fun navigateTo(destination: N) {
        _navigation.value = destination
    }

    abstract fun dispatcherViewAction(action: A)
}