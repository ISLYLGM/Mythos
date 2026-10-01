package com.example.mythos.screens

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mythos.ui.theme.MythosTheme
import java.util.Locale

private val Cream = Color(0xFFFBF7EF)
private val PurpleTop = Color(0xFF61388A)
private val PurpleMain = Color(0xFF5B2D91)
private val PurpleDark = Color(0xFF321B4B)
private val TextMuted = Color(0xFF756B7E)
private val Lilac = Color(0xFFEDE3F4)
private val LilacLight = Color(0xFFF7F2F9)
private val FieldBorder = Color(0xFFE2D8E9)

data class ItemSacola(
    val id: Int,
    val nome: String,
    val descricao: String,
    val preco: Double,
    val precoFormatado: String,
    val quantidade: Int
)

@Composable
fun SacolaScreen(
    onNavigate: (String) -> Unit = {},
    onCheckout: ((List<ItemSacola>) -> Unit)? = null,
    cartItems: SnapshotStateList<ItemSacola>? = null
) {
    val items = cartItems ?: remember {
        mutableStateListOf(
            ItemSacola(1, "Néctar de Poseidon", "Cítrico e refrescante", 16.90, "R$ 16,90", 1),
            ItemSacola(2, "Latte de Afrodite", "Floral e cremoso", 15.00, "R$ 15,00", 2),
            ItemSacola(3, "Bolo de Mel de Hera", "Doce e macio", 10.00, "R$ 10,00", 1)
        )
    }
    var checkoutRequested by remember { mutableStateOf(false) }
    val subtotal = items.sumOf { it.preco * it.quantidade }
    val serviceFee = if (items.isNotEmpty()) 2.50 else 0.0
    val total = subtotal + serviceFee
    val currency = remember { Locale("pt", "BR") }

    Scaffold(
        containerColor = Cream,
        bottomBar = {
            NavigationBar(containerColor = Color.White, tonalElevation = 7.dp) {
                val colors = NavigationBarItemDefaults.colors(selectedIconColor = PurpleMain, selectedTextColor = PurpleMain, indicatorColor = Lilac)
                NavigationBarItem(false, { onNavigate("home") }, icon = { Icon(Icons.Default.Home, contentDescription = "Início") }, label = { Text("Início", fontSize = 10.sp) }, colors = colors)
                NavigationBarItem(false, { onNavigate("cardapio") }, icon = { Icon(Icons.Default.Coffee, contentDescription = "Cardápio") }, label = { Text("Cardápio", fontSize = 10.sp) }, colors = colors)
                NavigationBarItem(true, { onNavigate("sacola") }, icon = { Icon(Icons.Default.ShoppingBag, contentDescription = "Sacola") }, label = { Text("Sacola", fontSize = 10.sp) }, colors = colors)
                NavigationBarItem(false, { onNavigate("perfil") }, icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") }, label = { Text("Perfil", fontSize = 10.sp) }, colors = colors)
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).background(Cream),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 24.dp)
        ) {
            item {
                Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(27.dp)).background(Brush.verticalGradient(listOf(PurpleTop, PurpleDark))).padding(21.dp)) {
                    Text("MYTOS • SUA JORNADA", color = Color.White.copy(alpha = 0.82f), fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                    Spacer(Modifier.height(9.dp))
                    Text("Sua sacola", color = Color.White, fontSize = 27.sp, fontWeight = FontWeight.ExtraBold, modifier = Modifier.semantics { heading() })
                    Spacer(Modifier.height(5.dp))
                    Text("Tudo pronto para a sua próxima pausa.", color = Color.White.copy(alpha = 0.82f), fontSize = 12.sp)
                }
            }

            if (items.isEmpty()) {
                item {
                    Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(24.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                        Column(Modifier.fillMaxWidth().padding(horizontal = 22.dp, vertical = 32.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(Modifier.size(68.dp).clip(CircleShape).background(Lilac), contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.ShoppingBag, contentDescription = null, tint = PurpleMain, modifier = Modifier.size(33.dp))
                            }
                            Spacer(Modifier.height(13.dp))
                            Text("Sua sacola está vazia", color = PurpleDark, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.height(5.dp))
                            Text("Que tal descobrir um novo sabor?", color = TextMuted, fontSize = 12.sp, textAlign = TextAlign.Center)
                            Spacer(Modifier.height(15.dp))
                            Button(onClick = { onNavigate("cardapio") }, shape = RoundedCornerShape(16.dp), colors = ButtonDefaults.buttonColors(containerColor = PurpleMain)) {
                                Text("Explorar cardápio", fontWeight = FontWeight.Bold)
                                Spacer(Modifier.width(6.dp))
                                Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(17.dp))
                            }
                        }
                    }
                }
            } else {
                item {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text("Seu pedido", color = PurpleDark, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                        Text("${items.sumOf { it.quantidade }} itens", color = TextMuted, fontSize = 12.sp)
                    }
                }
                items(items, key = { it.id }) { item ->
                    val currentIndex = items.indexOfFirst { it.id == item.id }
                    if (currentIndex >= 0) {
                        ItemSacolaCard(
                            item = item,
                            onDecrease = {
                                val index = items.indexOfFirst { it.id == item.id }
                                if (index >= 0) {
                                    if (items[index].quantidade > 1) items[index] = items[index].copy(quantidade = items[index].quantidade - 1)
                                    else items.removeAt(index)
                                }
                            },
                            onIncrease = {
                                val index = items.indexOfFirst { it.id == item.id }
                                if (index >= 0) items[index] = items[index].copy(quantidade = items[index].quantidade + 1)
                            },
                            onRemove = { items.removeAll { it.id == item.id } }
                        )
                    }
                }
                item {
                    Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(22.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                        Column(Modifier.padding(17.dp)) {
                            Text("Resumo do pedido", color = PurpleDark, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.height(14.dp))
                            ResumoLinha("Subtotal", String.format(currency, "R$ %.2f", subtotal))
                            Spacer(Modifier.height(8.dp))
                            ResumoLinha("Taxa de serviço", String.format(currency, "R$ %.2f", serviceFee))
                            Spacer(Modifier.height(13.dp))
                            Box(Modifier.fillMaxWidth().height(1.dp).background(FieldBorder))
                            Spacer(Modifier.height(13.dp))
                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Text("Total", color = PurpleDark, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                                Text(String.format(currency, "R$ %.2f", total), color = PurpleMain, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                            }
                        }
                    }
                }
                item {
                    Column {
                        Button(
                            onClick = {
                                if (onCheckout != null) onCheckout(items.toList())
                                else checkoutRequested = true
                            },
                            modifier = Modifier.fillMaxWidth().height(54.dp),
                            shape = RoundedCornerShape(17.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PurpleMain, contentColor = Color.White)
                        ) {
                            Text("CONTINUAR PARA FINALIZAÇÃO", fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.6.sp)
                            Spacer(Modifier.width(7.dp))
                            Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(17.dp))
                        }
                        if (checkoutRequested) {
                            Spacer(Modifier.height(8.dp))
                            Text("A finalização do pedido ainda precisa ser conectada.", color = TextMuted, fontSize = 12.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                        }
                    }
                }
                item {
                    Text("Preparado com carinho pela equipe MYTOS.", color = TextMuted, fontSize = 11.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp))
                }
            }
        }
    }
}

@Composable
fun ItemSacolaCard(item: ItemSacola, onRemove: () -> Unit, onDecrease: () -> Unit = {}, onIncrease: () -> Unit = {}) {
    val icon = when {
        item.nome.contains("bolo", ignoreCase = true) || item.nome.contains("cookie", ignoreCase = true) -> Icons.Default.Cake
        item.nome.contains("néctar", ignoreCase = true) || item.nome.contains("gelado", ignoreCase = true) -> Icons.Default.LocalCafe
        else -> Icons.Default.Coffee
    }
    Card(Modifier.fillMaxWidth(), shape = RoundedCornerShape(21.dp), colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.cardElevation(2.dp)) {
        Row(Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(68.dp).clip(RoundedCornerShape(17.dp)).background(Brush.verticalGradient(listOf(Lilac, LilacLight))), contentAlignment = Alignment.Center) {
                Icon(icon, contentDescription = null, tint = PurpleMain, modifier = Modifier.size(33.dp))
            }
            Spacer(Modifier.width(11.dp))
            Column(Modifier.weight(1f)) {
                Text(item.nome, color = PurpleDark, fontSize = 13.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(Modifier.height(3.dp))
                Text(item.descricao, color = TextMuted, fontSize = 10.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(Modifier.height(7.dp))
                Text(item.precoFormatado, color = PurpleMain, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.width(6.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    QuantityButton(Icons.Default.Remove, "Diminuir quantidade", onDecrease, enabled = true)
                    Text("${item.quantidade}", color = PurpleDark, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 7.dp))
                    QuantityButton(Icons.Default.Add, "Aumentar quantidade", onIncrease, enabled = true)
                }
                Spacer(Modifier.height(7.dp))
                androidx.compose.material3.IconButton(onClick = onRemove, modifier = Modifier.size(30.dp)) {
                    Icon(Icons.Default.Delete, contentDescription = "Remover ${item.nome}", tint = PurpleMain, modifier = Modifier.size(18.dp))
                }
            }
        }
    }
}

@Composable
private fun QuantityButton(icon: ImageVector, label: String, onClick: () -> Unit, enabled: Boolean) {
    Box(Modifier.size(28.dp).clip(CircleShape).background(if (enabled) Lilac else LilacLight).clickable(enabled = enabled, onClick = onClick), contentAlignment = Alignment.Center) {
        Icon(icon, contentDescription = label, tint = PurpleMain, modifier = Modifier.size(15.dp))
    }
}

@Composable
fun ResumoLinha(titulo: String, valor: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(titulo, color = TextMuted, fontSize = 12.sp)
        Text(valor, color = PurpleDark, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SacolaScreenPreview() {
    MythosTheme { SacolaScreen() }
}
