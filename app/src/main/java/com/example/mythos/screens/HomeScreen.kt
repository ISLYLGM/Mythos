package com.example.mythos.screens

import android.graphics.BitmapFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mythos.ui.theme.MythosTheme
import com.example.mythos.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private val Cream = Color(0xFFFBF7EF)
private val PurpleTop = Color(0xFF61388A)
private val PurpleMain = Color(0xFF5B2D91)
private val PurpleDark = Color(0xFF321B4B)
private val TextMuted = Color(0xFF756B7E)
private val Lilac = Color(0xFFEDE3F4)
private val LilacLight = Color(0xFFF7F2F9)
private val SoftGreen = Color(0xFFE5F0DA)
private val SoftYellow = Color(0xFFFFF0C7)

@Composable
fun HomeScreen(onNavigate: (String) -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
        ) {
            HomeHeader(onProfileClick = { onNavigate("perfil") })

            Column(Modifier.fillMaxWidth().padding(horizontal = 22.dp)) {
                Spacer(Modifier.height(23.dp))
                Text(
                    "Olá, seja bem-vindo! ✨",
                    color = PurpleDark,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.semantics { heading() }
                )
                Spacer(Modifier.height(5.dp))
                Text("Que tal descobrir um novo sabor hoje?", color = TextMuted, fontSize = 14.sp)
                Spacer(Modifier.height(20.dp))

                HomeBanner(onClick = { onNavigate("cardapio") })
                Spacer(Modifier.height(27.dp))

                SectionHeading("Explore o cardápio", "Ver tudo") { onNavigate("cardapio") }
                Spacer(Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(13.dp)
                ) {
                    CategoryItem("Bebidas", Icons.Default.Coffee) { onNavigate("cardapio") }
                    CategoryItem("Doces", Icons.Default.Cake) { onNavigate("cardapio") }
                    CategoryItem("Salgados", Icons.Default.Fastfood) { onNavigate("cardapio") }
                }
                Spacer(Modifier.height(28.dp))

                SectionHeading("Destaques do MYTOS", "Ver cardápio") { onNavigate("cardapio") }
                Spacer(Modifier.height(13.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(13.dp)
                ) {
                    ProductCard("Café de Apolo", "Café inspirado no deus grego do Sol.", "R$ 14,90", "img/pedidos/cafe_de_apolo.webp") { onNavigate("cardapio") }
                    ProductCard("Néctar de Poseidon", "Uma bebida inspirada nos mares.", "R$ 16,90", "img/pedidos/nectar_de_poseidon.webp") { onNavigate("cardapio") }
                    ProductCard("Torta de Hera", "Uma sobremesa digna dos deuses.", "R$ 18,90", "img/pedidos/torta_de_hera.webp") { onNavigate("cardapio") }
                }
                Spacer(Modifier.height(27.dp))

                MascotsCard()
                Spacer(Modifier.height(25.dp))

                Column(Modifier.fillMaxWidth().padding(horizontal = 14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFD2A94E), modifier = Modifier.size(24.dp))
                    Spacer(Modifier.height(7.dp))
                    Text("Café, sabor e lendas.", color = PurpleDark, fontSize = 17.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                    Spacer(Modifier.height(4.dp))
                    Text("Um universo inspirado em mitologias.", color = TextMuted, fontSize = 12.sp, textAlign = TextAlign.Center)
                }
                Spacer(Modifier.height(25.dp))
            }
        }

        HomeBottomNavigation(selected = "home", onNavigate = onNavigate)
    }
}

@Composable
private fun HomeHeader(onProfileClick: () -> Unit) {
    Box(
        Modifier.fillMaxWidth().height(220.dp)
            .clip(RoundedCornerShape(bottomStart = 36.dp, bottomEnd = 36.dp))
            .background(Brush.verticalGradient(listOf(PurpleTop, PurpleDark)))
    ) {
        Box(Modifier.align(Alignment.TopEnd).padding(top = 27.dp, end = 100.dp).size(11.dp).clip(CircleShape).background(SoftYellow))
        Box(Modifier.align(Alignment.BottomStart).padding(start = 120.dp, bottom = 76.dp).size(16.dp).clip(CircleShape).background(SoftGreen.copy(alpha = 0.9f)))
        Column(
            Modifier.fillMaxSize().padding(start = 22.dp, end = 22.dp, top = 38.dp, bottom = 21.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(48.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.15f)), contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(R.drawable.mythos_logo_vector),
                            contentDescription = "Logo MYTOS",
                            modifier = Modifier.size(34.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                    Spacer(Modifier.width(11.dp))
                    Column {
                        Text("MYTOS", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 2.sp)
                        Text("CAFÉ • MITOLOGIA • EXPERIÊNCIA", color = Color.White.copy(alpha = 0.78f), fontSize = 8.sp, letterSpacing = 0.8.sp)
                    }
                }
                Box(
                    Modifier.size(44.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.15f)).clickable(onClick = onProfileClick),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.AccountCircle, contentDescription = "Abrir perfil", tint = Color.White, modifier = Modifier.size(28.dp))
                }
            }
            Column {
                Text("Sabores que contam histórias.", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(4.dp))
                Text("Descubra uma experiência diferente a cada visita.", color = Color.White.copy(alpha = 0.82f), fontSize = 12.sp)
            }
        }
    }
}

@Composable
private fun HomeBanner(onClick: () -> Unit) {
    Box(
        Modifier.fillMaxWidth().height(166.dp).clip(RoundedCornerShape(25.dp))
            .background(Brush.horizontalGradient(listOf(PurpleMain, Color(0xFF8A62AD))))
            .clickable(onClick = onClick)
    ) {
        Column(Modifier.fillMaxSize().padding(start = 20.dp, top = 18.dp, bottom = 18.dp, end = 112.dp), verticalArrangement = Arrangement.Center) {
            Text("O sabor dos deuses", color = Color.White, fontSize = 19.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(Modifier.height(6.dp))
            Text("Explore bebidas e doces inspirados em diferentes mitologias.", color = Color.White.copy(alpha = 0.88f), fontSize = 12.sp, lineHeight = 17.sp)
            Spacer(Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("EXPLORAR", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                Spacer(Modifier.width(5.dp))
                Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Color.White, modifier = Modifier.size(15.dp))
            }
        }
        Box(Modifier.align(Alignment.CenterEnd).padding(end = 17.dp).size(78.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.15f)), contentAlignment = Alignment.Center) {
            Icon(Icons.Default.Coffee, contentDescription = null, tint = Color.White, modifier = Modifier.size(42.dp))
        }
    }
}

@Composable
private fun SectionHeading(title: String, action: String, onActionClick: () -> Unit) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(title, color = PurpleDark, fontSize = 17.sp, fontWeight = FontWeight.Bold)
        Text(action, color = PurpleMain, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.clickable(onClick = onActionClick).padding(4.dp))
    }
}

@Composable
private fun CategoryItem(title: String, icon: ImageVector, onClick: () -> Unit) {
    Column(Modifier.width(88.dp).clickable(onClick = onClick), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(Modifier.size(66.dp).clip(RoundedCornerShape(20.dp)).background(Lilac), contentAlignment = Alignment.Center) {
            Icon(icon, contentDescription = null, tint = PurpleMain, modifier = Modifier.size(30.dp))
        }
        Spacer(Modifier.height(7.dp))
        Text(title, color = PurpleDark, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun ProductCard(name: String, description: String, price: String, imagePath: String, onClick: () -> Unit) {
    Column(
        Modifier.width(205.dp).clip(RoundedCornerShape(23.dp)).background(Color.White)
            .clickable(onClick = onClick).padding(13.dp)
    ) {
        Box(Modifier.fillMaxWidth().height(108.dp).clip(RoundedCornerShape(17.dp)).background(Brush.verticalGradient(listOf(Lilac, LilacLight))), contentAlignment = Alignment.Center) {
            PedidoAssetImage(assetPath = imagePath, description = name)
        }
        Spacer(Modifier.height(11.dp))
        Text(name, color = PurpleDark, fontSize = 14.sp, fontWeight = FontWeight.Bold, maxLines = 1)
        Spacer(Modifier.height(4.dp))
        Text(description, color = TextMuted, fontSize = 10.sp, lineHeight = 14.sp, maxLines = 2, minLines = 2)
        Spacer(Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(price, color = PurpleMain, fontSize = 14.sp, fontWeight = FontWeight.ExtraBold)
            Box(Modifier.size(30.dp).clip(CircleShape).background(PurpleMain), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Ver $name", tint = Color.White, modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
private fun PedidoAssetImage(assetPath: String, description: String) {
    val context = LocalContext.current
    var image by remember(assetPath) { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(assetPath) {
        image = withContext(Dispatchers.IO) {
            runCatching {
                context.assets.open(assetPath).use { stream ->
                    BitmapFactory.decodeStream(stream)?.asImageBitmap()
                }
            }.getOrNull()
        }
    }

    image?.let { bitmap ->
        Image(
            bitmap = bitmap,
            contentDescription = description,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun MascotsCard() {
    Column(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(25.dp))
            .background(Brush.horizontalGradient(listOf(Color(0xFFE8DDEE), Color(0xFFF8F0E0))))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Column(Modifier.weight(1f)) {
                Text("Conheça nossos personagens", color = PurpleDark, fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(Modifier.height(5.dp))
                Text("Leve um pedacinho do MYTOS com você: conheça nossa coleção de brinquedos.", color = TextMuted, fontSize = 11.sp, lineHeight = 16.sp)
            }
            Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFD2A94E), modifier = Modifier.size(23.dp))
        }
        Spacer(Modifier.height(14.dp))
        Image(
            painter = painterResource(R.drawable.brinquedos_mascotes),
            contentDescription = "Brinquedos dos mascotes Iago, Mika e Bast",
            modifier = Modifier.fillMaxWidth().height(176.dp).clip(RoundedCornerShape(17.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(9.dp))
        Text("Iago  •  Mika  •  Bast", color = PurpleDark, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Text("Brinquedos colecionáveis MYTOS", color = PurpleMain, fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun HomeBottomNavigation(selected: String, onNavigate: (String) -> Unit) {
    NavigationBar(containerColor = Color.White, tonalElevation = 6.dp) {
        val navColors = NavigationBarItemDefaults.colors(selectedIconColor = PurpleMain, selectedTextColor = PurpleMain, indicatorColor = Lilac)
        NavigationBarItem(selected == "home", { onNavigate("home") }, icon = { Icon(Icons.Default.Home, contentDescription = "Início") }, label = { Text("Início", fontSize = 10.sp) }, colors = navColors)
        NavigationBarItem(selected == "cardapio", { onNavigate("cardapio") }, icon = { Icon(Icons.Default.Coffee, contentDescription = "Cardápio") }, label = { Text("Cardápio", fontSize = 10.sp) }, colors = navColors)
        NavigationBarItem(selected == "sacola", { onNavigate("sacola") }, icon = { Icon(Icons.Default.ShoppingBag, contentDescription = "Sacola") }, label = { Text("Sacola", fontSize = 10.sp) }, colors = navColors)
        NavigationBarItem(selected == "perfil", { onNavigate("perfil") }, icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") }, label = { Text("Perfil", fontSize = 10.sp) }, colors = navColors)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MythosTheme { HomeScreen() }
}

