package com.marcelo.dailytrack.base_ui.common

abstract class BaseViewState(
    open val isLoading: Boolean,
    open val error: Throwable?,
)