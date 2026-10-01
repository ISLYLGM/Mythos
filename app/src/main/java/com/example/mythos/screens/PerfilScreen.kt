package com.example.mythos.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mythos.ui.theme.MythosTheme

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
fun PerfilScreen(
    onNavigate: (String) -> Unit = {},
    nomeUsuario: String = "Visitante",
    emailUsuario: String = "Entre para sincronizar seus dados",
    onEditProfile: (() -> Unit)? = null,
    onOrdersClick: (() -> Unit)? = null,
    onFavoritesClick: (() -> Unit)? = null,
    onNotificationsClick: (() -> Unit)? = null,
    onSettingsClick: (() -> Unit)? = null,
    onLogout: (() -> Unit)? = null
) {
    val context = LocalContext.current
    fun unavailable() = Toast.makeText(context, "Esta função ainda precisa ser conectada.", Toast.LENGTH_SHORT).show()

    Scaffold(
        containerColor = Cream,
        bottomBar = {
            NavigationBar(containerColor = Color.White, tonalElevation = 7.dp) {
                val colors = NavigationBarItemDefaults.colors(selectedIconColor = PurpleMain, selectedTextColor = PurpleMain, indicatorColor = Lilac)
                NavigationBarItem(false, { onNavigate("home") }, icon = { Icon(Icons.Default.Home, contentDescription = "Início") }, label = { Text("Início", fontSize = 10.sp) }, colors = colors)
                NavigationBarItem(false, { onNavigate("cardapio") }, icon = { Icon(Icons.Default.Coffee, contentDescription = "Cardápio") }, label = { Text("Cardápio", fontSize = 10.sp) }, colors = colors)
                NavigationBarItem(false, { onNavigate("sacola") }, icon = { Icon(Icons.Default.ShoppingBag, contentDescription = "Sacola") }, label = { Text("Sacola", fontSize = 10.sp) }, colors = colors)
                NavigationBarItem(true, { onNavigate("perfil") }, icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") }, label = { Text("Perfil", fontSize = 10.sp) }, colors = colors)
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).background(Cream),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 25.dp)
        ) {
            item {
                Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(27.dp)).background(Brush.verticalGradient(listOf(PurpleTop, PurpleDark))).padding(21.dp)) {
                    Text("MYTOS • SUA CONTA", color = Color.White.copy(alpha = 0.82f), fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                    Spacer(Modifier.height(9.dp))
                    Text("Meu perfil", color = Color.White, fontSize = 27.sp, fontWeight = FontWeight.ExtraBold, modifier = Modifier.semantics { heading() })
                    Spacer(Modifier.height(5.dp))
                    Text("Sua jornada pelo universo MYTOS.", color = Color.White.copy(alpha = 0.82f), fontSize = 12.sp)
                }
            }
            item {
                Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(23.dp), colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.cardElevation(2.dp)) {
                    Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(65.dp).clip(CircleShape).background(Lilac), contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.Person, contentDescription = null, tint = PurpleMain, modifier = Modifier.size(36.dp))
                        }
                        Spacer(Modifier.width(13.dp))
                        Column(Modifier.weight(1f)) {
                            Text(nomeUsuario, color = PurpleDark, fontSize = 18.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            Spacer(Modifier.height(3.dp))
                            Text(emailUsuario, color = TextMuted, fontSize = 11.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
                        }
                        IconButton(onClick = { onEditProfile?.invoke() ?: unavailable() }) {
                            Icon(Icons.Default.Edit, contentDescription = "Editar perfil", tint = PurpleMain)
                        }
                    }
                }
            }
            item {
                Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(22.dp), colors = CardDefaults.cardColors(containerColor = LilacLight)) {
                    Row(Modifier.fillMaxWidth().padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(42.dp).clip(CircleShape).background(SoftYellow), contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.Star, contentDescription = null, tint = PurpleMain, modifier = Modifier.size(23.dp))
                        }
                        Spacer(Modifier.width(11.dp))
                        Column(Modifier.weight(1f)) {
                            Text("Sua história continua", color = PurpleDark, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.height(3.dp))
                            Text("Explore o cardápio e encontre seu próximo favorito.", color = TextMuted, fontSize = 11.sp, lineHeight = 16.sp)
                        }
                    }
                }
            }
            item {
                Text("Minha conta", color = PurpleDark, fontSize = 17.sp, fontWeight = FontWeight.Bold)
            }
            item {
                Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(22.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                    Column {
                        PerfilOpcao(Icons.Default.ShoppingBag, "Meus pedidos", "Acompanhe seus pedidos", onOrdersClick ?: { unavailable() })
                        PerfilDivisor()
                        PerfilOpcao(Icons.Default.FavoriteBorder, "Favoritos", "Seus sabores preferidos", onFavoritesClick ?: { unavailable() })
                        PerfilDivisor()
                        PerfilOpcao(Icons.Default.Notifications, "Notificações", "Preferências de avisos", onNotificationsClick ?: { unavailable() })
                        PerfilDivisor()
                        PerfilOpcao(Icons.Default.Settings, "Configurações", "Preferências do aplicativo", onSettingsClick ?: { unavailable() })
                        PerfilDivisor()
                        PerfilOpcao(Icons.Default.AccessibilityNew, "Acessibilidade", "Recursos acessíveis do MYTOS") { onNavigate("acessibilidade") }
                    }
                }
            }
            item {
                OutlinedButton(
                    onClick = { onLogout?.invoke() ?: unavailable() },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(contentColor = PurpleMain)
                ) {
                    Icon(Icons.Default.Logout, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(7.dp))
                    Text("Sair da conta", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }
            item {
                Text("Café, sabor e lendas.", color = TextMuted, fontSize = 11.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(top = 3.dp))
            }
        }
    }
}

@Composable
fun PerfilOpcao(icon: ImageVector, titulo: String, descricao: String, onClick: () -> Unit) {
    Row(
        Modifier.fillMaxWidth().clickable(onClick = onClick).padding(horizontal = 14.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.size(40.dp).clip(RoundedCornerShape(13.dp)).background(Lilac), contentAlignment = Alignment.Center) {
            Icon(icon, contentDescription = null, tint = PurpleMain, modifier = Modifier.size(20.dp))
        }
        Spacer(Modifier.width(11.dp))
        Column(Modifier.weight(1f)) {
            Text(titulo, color = PurpleDark, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(2.dp))
            Text(descricao, color = TextMuted, fontSize = 10.sp)
        }
        Icon(Icons.Default.ArrowForwardIos, contentDescription = null, tint = TextMuted, modifier = Modifier.size(14.dp))
    }
}

@Composable
fun PerfilDivisor() {
    Box(Modifier.fillMaxWidth().padding(horizontal = 14.dp).height(1.dp).background(Color(0xFFEAE3ED)))
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PerfilScreenPreview() {
    MythosTheme { PerfilScreen() }
}
