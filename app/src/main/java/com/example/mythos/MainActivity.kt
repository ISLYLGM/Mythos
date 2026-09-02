package com.example.mythos

import com.example.mythos.screens.CadastroScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mythos.screens.LoginScreen
import com.example.mythos.screens.SplashScreen
import com.example.mythos.ui.theme.MythosTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MythosTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ) {
                    composable("splash") {
                        SplashScreen(
                            onLoginClick = {
                                // Navega para o login sem remover a splash se você quiser poder voltar para ela
                                navController.navigate("login")
                            }
                        )
                    }

                    composable("login") {
                        LoginScreen(
                            onBackClick = {
                                navController.popBackStack() // Volta para a Splash
                            },
                            onSignUpClick = {
                                navController.navigate("register") // Vai para a tela de cadastro
                            }
                        )
                    }

                    composable("register") {
                        CadastroScreen(
                            onBackClick = {
                                navController.popBackStack() // Volta para a tela de Login
                            },
                            onLoginClick = {
                                navController.popBackStack() // Volta para a tela de Login
                            }
                        )
                    }
                }
            }
        }
    }
}