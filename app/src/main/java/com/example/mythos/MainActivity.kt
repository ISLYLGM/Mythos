package com.example.mythos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.mythos.screens.LoginScreen
import com.example.mythos.screens.SplashScreen
import com.example.mythos.ui.theme.MythosTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MythosTheme {

                var telaAtual by remember {
                    mutableStateOf("splash")
                }

                if (telaAtual == "splash") {

                    SplashScreen(
                        onLoginClick = {
                            telaAtual = "login"
                        }
                    )

                } else {

                    LoginScreen(
                        onBackClick = {
                            telaAtual = "splash"
                        }
                    )

                }
            }
        }
    }
}