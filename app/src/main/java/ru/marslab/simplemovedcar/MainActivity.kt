package ru.marslab.simplemovedcar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.marslab.simplemovedcar.presentation.theme.SimpleMovedCarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleMovedCarTheme {
            }
        }
    }
}
