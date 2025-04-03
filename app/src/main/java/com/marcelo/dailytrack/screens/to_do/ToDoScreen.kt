package com.marcelo.dailytrack.screens.to_do

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.marcelo.dailytrack.base_ui.common.BaseComposeScreen
import org.koin.androidx.compose.koinViewModel

@Composable
@Preview
fun ToDoScreen(
    viewModel: TaskViewModel = koinViewModel()
) {
    BaseComposeScreen(
        viewState = viewModel.viewState,
        action = viewModel::dispatcherViewAction,
        initCalls = {

        }
    ) { state, action ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("ToDo")
        }
    }
}