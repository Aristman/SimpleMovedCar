package ru.marslab.simplemovedcar.presentation.mainscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class MainScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel = getViewModel<MainViewModel>()
        viewModel.setNavigator(LocalNavigator.currentOrThrow)
        MainScreenView(viewModel)
    }
}

@Composable
private fun MainScreenView(viewModel: MainViewModel) {
    Surface(
        color = Color.LightGray,
        modifier = Modifier.fillMaxSize()
    ) {
    }
}
