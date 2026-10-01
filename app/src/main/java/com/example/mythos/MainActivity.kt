package com.example.mythos

import android.graphics.Color as AndroidColor
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mythos.screens.AcessibilidadeScreen
import com.example.mythos.screens.CadastroScreen
import com.example.mythos.screens.CardapioScreen
import com.example.mythos.screens.HomeScreen
import com.example.mythos.screens.InfoScreen
import com.example.mythos.screens.ItemSacola
import com.example.mythos.screens.LoginScreen
import com.example.mythos.screens.PerfilScreen
import com.example.mythos.screens.ProdutoCardapio
import com.example.mythos.screens.SacolaScreen
import com.example.mythos.screens.SplashScreen
import com.example.mythos.ui.theme.MythosTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val systemPurple = AndroidColor.rgb(58, 26, 99)
        window.setBackgroundDrawable(ColorDrawable(systemPurple))
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = AndroidColor.TRANSPARENT
        window.navigationBarColor = AndroidColor.TRANSPARENT
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            window.isStatusBarContrastEnforced = false
            window.isNavigationBarContrastEnforced = false
        }
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }

        setContent {
            MythosTheme {
                val navController = rememberNavController()
                // Sacola compartilhada pelas telas de cardápio, detalhes e sacola.
                val cartItems = remember { mutableStateListOf<ItemSacola>() }

                fun addToCart(id: Int, name: String, description: String, priceText: String) {
                    val price = priceText
                        .replace("R$", "")
                        .trim()
                        .replace(".", "")
                        .replace(",", ".")
                        .toDoubleOrNull() ?: 0.0
                    val index = cartItems.indexOfFirst { it.id == id }
                    if (index >= 0) {
                        cartItems[index] = cartItems[index].copy(quantidade = cartItems[index].quantidade + 1)
                    } else {
                        cartItems.add(
                            ItemSacola(
                                id = id,
                                nome = name,
                                descricao = description,
                                preco = price,
                                precoFormatado = String.format(Locale("pt", "BR"), "R$ %.2f", price),
                                quantidade = 1
                            )
                        )
                    }
                }

                Box(Modifier.fillMaxSize()) {
                    NavHost(
                        navController = navController,
                        startDestination = "splash",
                        modifier = Modifier.fillMaxSize(),
                        enterTransition = { fadeIn(tween(220)) },
                        exitTransition = { fadeOut(tween(180)) },
                        popEnterTransition = { fadeIn(tween(220)) },
                        popExitTransition = { fadeOut(tween(180)) }
                    ) {
                        composable("splash") {
                            SplashScreen(onFinished = {
                                navController.navigate("login") {
                                    popUpTo("splash") { inclusive = true }
                                    launchSingleTop = true
                                }
                            })
                        }

                        composable("login") {
                            LoginScreen(
                                onBackClick = { navController.popBackStack() },
                                onSignUpClick = { navController.navigate("cadastro") },
                                onLoginSuccess = {
                                    navController.navigate("home") {
                                        popUpTo("login") { inclusive = true }
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable("cadastro") {
                            CadastroScreen(
                                onBackClick = { navController.popBackStack() },
                                onLoginClick = { navController.popBackStack() },
                                onCadastroSuccess = {
                                    navController.navigate("acessibilidade") {
                                        popUpTo("login") { inclusive = true }
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable("acessibilidade") {
                            AcessibilidadeScreen(
                                onAtivar = {
                                    navController.navigate("home") {
                                        popUpTo("acessibilidade") { inclusive = true }
                                        launchSingleTop = true
                                    }
                                },
                                onContinuar = {
                                    navController.navigate("home") {
                                        popUpTo("acessibilidade") { inclusive = true }
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable("home") {
                            HomeScreen(onNavigate = { route -> navController.navigateToMythos(route) })
                        }

                        composable("cardapio") {
                            CardapioScreen(
                                onNavigate = { route -> navController.navigateToMythos(route) },
                                onAddToBag = { product ->
                                    addToCart(product.id, product.nome, product.descricao, product.preco)
                                    Toast.makeText(this@MainActivity, "${product.nome} adicionado à sacola", Toast.LENGTH_SHORT).show()
                                },
                                onProductClick = { product -> navController.navigate("produto/${product.id}") }
                            )
                        }

                        composable(
                            route = "produto/{productId}",
                            arguments = listOf(navArgument("productId") { type = NavType.IntType })
                        ) { entry ->
                            val productId = entry.arguments?.getInt("productId") ?: -1
                            InfoScreen(
                                productId = productId,
                                onBackClick = { navController.popBackStack() },
                                onAddToBag = { product ->
                                    addToCart(product.id, product.nome, product.descricao, product.preco)
                                    Toast.makeText(this@MainActivity, "${product.nome} adicionado à sacola", Toast.LENGTH_SHORT).show()
                                    navController.navigate("sacola") { launchSingleTop = true }
                                }
                            )
                        }

                        composable("sacola") {
                            SacolaScreen(
                                onNavigate = { route -> navController.navigateToMythos(route) },
                                cartItems = cartItems
                            )
                        }

                        composable("perfil") {
                            PerfilScreen(onNavigate = { route -> navController.navigateToMythos(route) })
                        }
                    }

                    // Proteção de fundo para as barras transparentes do Android.
                    Box(
                        Modifier
                            .align(Alignment.TopStart)
                            .fillMaxWidth()
                            .windowInsetsTopHeight(WindowInsets.statusBars)
                            .background(Color(0xFF3A1A63))
                    )
                    Box(
                        Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                            .windowInsetsBottomHeight(WindowInsets.navigationBars)
                            .background(Color(0xFF3A1A63))
                    )
                }
            }
        }
    }
}

private fun androidx.navigation.NavHostController.navigateToMythos(route: String) {
    if (route.isBlank()) return
    navigate(route) {
        launchSingleTop = true
        restoreState = true
    }
}
