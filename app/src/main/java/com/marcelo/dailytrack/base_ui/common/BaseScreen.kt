package com.marcelo.dailytrack.base_ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

typealias Action<A> = (A) -> Unit

@Composable
fun <V, A> BaseComposeScreen(
    viewState: V,
    action: Action<A>,
    initCalls: () -> Unit = {},
    content: @Composable (V, Action<A>) -> Unit
) {
    LaunchedEffect(Unit) {
        initCalls()
    }
    content(viewState, action)
}