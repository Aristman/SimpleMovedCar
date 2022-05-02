package ru.marslab.simplemovedcar.presentation.mainscreen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.marslab.simplemovedcar.R
import ru.marslab.simplemovedcar.presentation.mainscreen.model.MainScreenAction

private const val CAR_HALF_WIDTH = 88
private const val CAR_HALF_HEIGHT = 44
private const val ANIMATION_RATIO = 4f

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
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp - CAR_HALF_WIDTH
    val screenHeightDp = configuration.screenHeightDp - CAR_HALF_HEIGHT
    val state = viewModel.state.collectAsState()
    val isMoved = state.value.isMoved
    val animation = spring<Dp>(
        dampingRatio = ANIMATION_RATIO,
        stiffness = Spring.StiffnessMedium
    )
    val paddingX = animateDpAsState(
        targetValue = if (isMoved) {
            Dp(state.value.startPosition.x.toFloat()) * screenWidthDp
        } else {
            Dp(state.value.stopPosition.x.toFloat()) * screenWidthDp
        },
        animationSpec = animation
    )
    val paddingY = animateDpAsState(
        targetValue = if (isMoved) {
            Dp(state.value.startPosition.y.toFloat()) * screenHeightDp
        } else {
            Dp(state.value.stopPosition.y.toFloat()) * screenHeightDp
        },
        animationSpec = animation
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
    ) {
        Image(
            painter = painterResource(id = R.drawable.car),
            contentDescription = null,
            modifier = Modifier
                .padding(start = paddingX.value, top = paddingY.value)
                .requiredSize(88.dp)
                .clickable { viewModel.sendAction(MainScreenAction.CarStartClick) }
        )
    }
}
