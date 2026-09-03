package com.example.mythos

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.mythos.screens.CadastroScreen
import com.example.mythos.screens.HomeScreen
import com.example.mythos.screens.LoginScreen
import com.example.mythos.screens.SplashScreen
import com.example.mythos.ui.theme.MythosTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(
            savedInstanceState
        )


        setContent {

            MythosTheme {

                val navController =
                    rememberNavController()


                NavHost(

                    navController =
                        navController,

                    startDestination =
                        "splash"
                ) {



                    composable("splash") {

                        SplashScreen(

                            onLoginClick = {

                                navController.navigate(
                                    "login"
                                )
                            }
                        )
                    }



                    composable("login") {

                        LoginScreen(

                            onBackClick = {

                                navController.popBackStack()

                            },

                            onSignUpClick = {

                                navController.navigate(
                                    "cadastro"
                                )

                            },

                            onLoginSuccess = {

                                navController.navigate(
                                    "home"
                                )

                            }
                        )
                    }



                    composable("cadastro") {

                        CadastroScreen(

                            onBackClick = {

                                navController.popBackStack()

                            },

                            onLoginClick = {

                                navController.popBackStack()

                            },

                            onCadastroSuccess = {

                                navController.navigate(
                                    "home"
                                )

                            }
                        )
                    }



                    composable("home") {

                        HomeScreen()

                    }
                }
            }
        }
    }
}