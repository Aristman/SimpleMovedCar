package ru.marslab.simplemovedcar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import ru.marslab.simplemovedcar.presentation.mainscreen.MainScreen
import ru.marslab.simplemovedcar.presentation.theme.SimpleMovedCarTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleMovedCarTheme {
                Navigator(screen = MainScreen())
            }
        }
    }
}
