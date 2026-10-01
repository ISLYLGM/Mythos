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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.input.ImeAction
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
private val FieldBorder = Color(0xFFE2D8E9)
private val Lilac = Color(0xFFEDE3F4)
private val LilacLight = Color(0xFFF7F2F9)
private val SoftGreen = Color(0xFFE5F0DA)
private val SoftYellow = Color(0xFFFFF0C7)

data class ProdutoCardapio(
    val id: Int,
    val nome: String,
    val descricao: String,
    val categoria: String,
    val preco: String,
    val imagemRes: Int
)

@Composable
fun CardapioScreen(
    onNavigate: (String) -> Unit = {},
    onAddToBag: ((ProdutoCardapio) -> Unit)? = null,
    onProductClick: (ProdutoCardapio) -> Unit = {}
) {
    val context = LocalContext.current
    var selectedCategory by rememberSaveable { mutableStateOf("Todos") }
    var searchText by rememberSaveable { mutableStateOf("") }

    val categories = listOf("Todos", "Cafés", "Gelados", "Especiais", "Doces")
    val products = remember {
        listOf(
            ProdutoCardapio(1, "Café de Apolo", "Espresso intenso com notas de chocolate.", "Cafés", "R$ 8,00", 0),
            ProdutoCardapio(2, "Latte de Afrodite", "Café cremoso com um toque floral.", "Cafés", "R$ 15,00", 0),
            ProdutoCardapio(3, "Cappuccino de Héstia", "Leite vaporizado, café e canela.", "Cafés", "R$ 15,00", 0),
            ProdutoCardapio(4, "Coado de Iago", "Café coado com notas de caramelo.", "Cafés", "R$ 12,00", 0),
            ProdutoCardapio(5, "Néctar de Poseidon", "Café tônico cítrico e refrescante.", "Especiais", "R$ 16,90", 0),
            ProdutoCardapio(6, "Mocha de Thor", "Café com chocolate e um toque de energia.", "Especiais", "R$ 17,00", 0),
            ProdutoCardapio(7, "Matcha de Ártemis", "Matcha gelado, suave e refrescante.", "Gelados", "R$ 15,00", 0),
            ProdutoCardapio(8, "Cold Brew de Anúbis", "Extração a frio, leve e marcante.", "Gelados", "R$ 14,00", 0),
            ProdutoCardapio(9, "Bolo de Mel de Hera", "Bolo macio com mel e especiarias.", "Doces", "R$ 10,00", 0),
            ProdutoCardapio(10, "Cookie de Freya", "Cookie amanteigado com chocolate.", "Doces", "R$ 8,00", 0)
        )
    }
    val filteredProducts = products.filter { product ->
        (selectedCategory == "Todos" || product.categoria == selectedCategory) &&
                (searchText.isBlank() || product.nome.contains(searchText.trim(), ignoreCase = true) || product.descricao.contains(searchText.trim(), ignoreCase = true))
    }

    Scaffold(
        containerColor = Cream,
        bottomBar = {
            NavigationBar(containerColor = Color.White, tonalElevation = 7.dp) {
                val colors = NavigationBarItemDefaults.colors(selectedIconColor = PurpleMain, selectedTextColor = PurpleMain, indicatorColor = Lilac)
                NavigationBarItem(false, { onNavigate("home") }, icon = { Icon(Icons.Default.Home, contentDescription = "Início") }, label = { Text("Início", fontSize = 10.sp) }, colors = colors)
                NavigationBarItem(true, { onNavigate("cardapio") }, icon = { Icon(Icons.Default.Coffee, contentDescription = "Cardápio") }, label = { Text("Cardápio", fontSize = 10.sp) }, colors = colors)
                NavigationBarItem(false, { onNavigate("sacola") }, icon = { Icon(Icons.Default.ShoppingBag, contentDescription = "Sacola") }, label = { Text("Sacola", fontSize = 10.sp) }, colors = colors)
                NavigationBarItem(false, { onNavigate("perfil") }, icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") }, label = { Text("Perfil", fontSize = 10.sp) }, colors = colors)
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).background(Cream),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 24.dp)
        ) {
            item {
                Column(
                    Modifier.fillMaxWidth().clip(RoundedCornerShape(27.dp))
                        .background(Brush.verticalGradient(listOf(PurpleTop, PurpleDark))).padding(21.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(9.dp).clip(CircleShape).background(SoftYellow))
                        Spacer(Modifier.width(8.dp))
                        Text("MYTOS • CAFÉ E MITOLOGIA", color = Color.White.copy(alpha = 0.82f), fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                    }
                    Spacer(Modifier.height(10.dp))
                    Text("Nosso cardápio", color = Color.White, fontSize = 27.sp, fontWeight = FontWeight.ExtraBold, modifier = Modifier.semantics { heading() })
                    Spacer(Modifier.height(5.dp))
                    Text("Escolha sua próxima história para saborear.", color = Color.White.copy(alpha = 0.82f), fontSize = 12.sp)
                }
            }

            item {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Pesquisar produtos", tint = PurpleMain) },
                    placeholder = { Text("O que você deseja saborear?", fontSize = 13.sp, color = TextMuted) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    shape = RoundedCornerShape(17.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = PurpleMain,
                        unfocusedBorderColor = FieldBorder,
                        focusedTextColor = PurpleDark,
                        unfocusedTextColor = PurpleDark,
                        cursorColor = PurpleMain
                    )
                )
            }

            item {
                Column {
                    Text("Categorias", color = PurpleDark, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(10.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(categories) { category ->
                            val selected = category == selectedCategory
                            Box(
                                Modifier.clip(RoundedCornerShape(50))
                                    .background(if (selected) PurpleMain else Color.White)
                                    .clickable { selectedCategory = category }
                                    .padding(horizontal = 15.dp, vertical = 10.dp)
                            ) {
                                Text(category, color = if (selected) Color.White else PurpleDark, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                }
            }

            item {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(if (selectedCategory == "Todos") "Todos os sabores" else selectedCategory, color = PurpleDark, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text("${filteredProducts.size} opções", color = TextMuted, fontSize = 11.sp)
                }
            }

            if (filteredProducts.isEmpty()) {
                item {
                    Column(Modifier.fillMaxWidth().padding(vertical = 28.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Nenhum sabor encontrado", color = PurpleDark, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(5.dp))
                        Text("Tente outro nome ou escolha outra categoria.", color = TextMuted, fontSize = 12.sp, textAlign = TextAlign.Center)
                    }
                }
            } else {
                items(filteredProducts.chunked(2)) { rowProducts ->
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        rowProducts.forEach { product ->
                            ProdutoCardapioCard(product, Modifier.weight(1f), onProductClick = { onProductClick(product) }) {
                                if (onAddToBag != null) {
                                    onAddToBag(product)
                                } else {
                                    Toast.makeText(context, "A adição à sacola ainda precisa ser conectada.", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                        if (rowProducts.size == 1) Spacer(Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun ProdutoCardapioCard(
    produto: ProdutoCardapio,
    modifier: Modifier = Modifier,
    onProductClick: () -> Unit = {},
    onAdd: () -> Unit
) {
    val icon = when (produto.categoria) {
        "Doces" -> Icons.Default.Cake
        "Gelados" -> Icons.Default.LocalCafe
        else -> Icons.Default.Coffee
    }
    Card(
        modifier = modifier.clickable(onClick = onProductClick),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(Modifier.padding(11.dp)) {
            Box(
                Modifier.fillMaxWidth().height(105.dp).clip(RoundedCornerShape(17.dp))
                    .background(Brush.verticalGradient(listOf(Lilac, LilacLight))),
                contentAlignment = Alignment.Center
            ) {
                Box(Modifier.size(62.dp).clip(CircleShape).background(Color.White), contentAlignment = Alignment.Center) {
                    Icon(icon, contentDescription = null, tint = PurpleMain, modifier = Modifier.size(33.dp))
                }
            }
            Spacer(Modifier.height(9.dp))
            Text(produto.nome, color = PurpleDark, fontSize = 14.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Spacer(Modifier.height(3.dp))
            Text(produto.descricao, color = TextMuted, fontSize = 10.sp, maxLines = 2, minLines = 2, overflow = TextOverflow.Ellipsis)
            Spacer(Modifier.height(9.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(produto.preco, color = PurpleMain, fontSize = 13.sp, fontWeight = FontWeight.ExtraBold)
                Box(Modifier.size(32.dp).clip(CircleShape).background(PurpleMain).clickable(onClick = onAdd), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Add, contentDescription = "Adicionar ${produto.nome} à sacola", tint = Color.White, modifier = Modifier.size(19.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CardapioScreenPreview() {
    MythosTheme { CardapioScreen() }
}
