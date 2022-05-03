package ru.marslab.simplemovedcar.presentation.mainscreen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.coroutineScope
import ru.marslab.simplemovedcar.R
import ru.marslab.simplemovedcar.presentation.mainscreen.model.MainScreenAction

private const val ANIMATION_DURATION = 1000
private val CAR_SIZE = 88.dp

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
    val densityDp = LocalDensity.current.density
    val state = viewModel.state.collectAsState()
    val animation = tween<Dp>(
        durationMillis = ANIMATION_DURATION,
        easing = LinearEasing
    )
    val paddingX = animateDpAsState(
        targetValue = state.value.stopPosition.x,
        animationSpec = animation
    )
    val paddingY = animateDpAsState(
        targetValue = state.value.stopPosition.y,
        animationSpec = animation
    )

    LaunchedEffect(key1 = true) {
        viewModel.setScreenSize(
            height = configuration.screenHeightDp.dp - CAR_SIZE,
            width = configuration.screenWidthDp.dp - CAR_SIZE
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .pointerInput(Unit) { clickHandler(viewModel, densityDp) }
    ) {
        Image(
            painter = painterResource(id = R.drawable.car),
            contentDescription = null,
            modifier = Modifier
                .offset(x = paddingX.value, y = paddingY.value)
                .requiredSize(88.dp)
                .clickable { viewModel.sendAction(MainScreenAction.CarStartClick) }
        )
    }
}

private suspend fun PointerInputScope.clickHandler(
    viewModel: MainViewModel,
    densityDp: Float
) {
    coroutineScope {
        while (true) {
            val position = awaitPointerEventScope {
                awaitFirstDown().position
            }
            viewModel.sendAction(
                MainScreenAction.FieldClick(
                    DpOffset(
                        (position.x / densityDp).dp - CAR_SIZE.div(2),
                        (position.y / densityDp).dp - CAR_SIZE.div(2)
                    )
                )
            )
        }
    }
}
