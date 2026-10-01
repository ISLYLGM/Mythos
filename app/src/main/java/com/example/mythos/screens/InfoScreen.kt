package com.example.mythos.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

data class InfoProduto(
    val id: Int,
    val nome: String,
    val descricao: String,
    val historia: String,
    val categoria: String,
    val preco: String
)

/** Catálogo que alimenta a tela de detalhes e o cardápio. */
val produtosMythos = listOf(
    InfoProduto(1, "Café de Apolo", "Espresso intenso com notas de chocolate.", "Inspirado em Apolo, deus grego associado ao Sol e às artes. Um espresso encorpado para iluminar a pausa.", "Cafés", "R$ 8,00"),
    InfoProduto(2, "Latte de Afrodite", "Café cremoso com um toque floral.", "Uma bebida delicada e aveludada, inspirada em Afrodite e na beleza dos pequenos rituais.", "Cafés", "R$ 15,00"),
    InfoProduto(3, "Cappuccino de Héstia", "Leite vaporizado, café e canela.", "Quentinho e acolhedor, este cappuccino evoca Héstia, guardiã do lar e do fogo da lareira.", "Cafés", "R$ 15,00"),
    InfoProduto(4, "Coado de Iago", "Café coado com notas de caramelo.", "Uma xícara suave para acompanhar boas histórias, com a companhia do Iago, nosso lobo-guará.", "Cafés", "R$ 12,00"),
    InfoProduto(5, "Néctar de Poseidon", "Café tônico cítrico e refrescante.", "Borbulhante e cítrico como uma onda, inspirado em Poseidon e nos mistérios do mar.", "Especiais", "R$ 16,90"),
    InfoProduto(6, "Mocha de Thor", "Café com chocolate e um toque de energia.", "A combinação intensa de café e chocolate traz a força de Thor para uma pausa cheia de sabor.", "Especiais", "R$ 17,00"),
    InfoProduto(7, "Matcha de Ártemis", "Matcha gelado, suave e refrescante.", "Uma bebida verde e leve, inspirada em Ártemis e nas caminhadas por paisagens naturais.", "Gelados", "R$ 15,00"),
    InfoProduto(8, "Cold Brew de Anúbis", "Extração a frio, leve e marcante.", "Extraído lentamente a frio, revela um sabor profundo e suave, inspirado nas lendas de Anúbis.", "Gelados", "R$ 14,00"),
    InfoProduto(9, "Bolo de Mel de Hera", "Bolo macio com mel e especiarias.", "Macio, perfumado e feito para compartilhar, com um toque doce digno da rainha do Olimpo.", "Doces", "R$ 10,00"),
    InfoProduto(10, "Cookie de Freya", "Cookie amanteigado com chocolate.", "Crocante por fora e macio por dentro, inspirado em Freya e nas histórias nórdicas.", "Doces", "R$ 8,00")
)

@Composable
fun InfoScreen(
    productId: Int,
    onBackClick: () -> Unit = {},
    onAddToBag: (InfoProduto) -> Unit = {}
) {
    val product = produtosMythos.firstOrNull { it.id == productId }
    if (product == null) {
        Column(Modifier.fillMaxSize().background(Cream).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text("Não encontramos esse produto", color = PurpleDark, fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Spacer(Modifier.height(14.dp))
            Button(onClick = onBackClick, colors = ButtonDefaults.buttonColors(containerColor = PurpleMain)) { Text("Voltar") }
        }
        return
    }

    val icon = when (product.categoria) {
        "Doces" -> Icons.Default.Cake
        "Gelados" -> Icons.Default.LocalCafe
        else -> Icons.Default.Coffee
    }
    Column(Modifier.fillMaxSize().background(Cream).verticalScroll(rememberScrollState())) {
        Box(
            Modifier.fillMaxWidth().height(350.dp)
                .background(Brush.verticalGradient(listOf(PurpleTop, PurpleDark)))
        ) {
            IconButton(onClick = onBackClick, modifier = Modifier.align(Alignment.TopStart).padding(start = 14.dp, top = 25.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.16f))) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
            }
            Box(Modifier.align(Alignment.TopEnd).padding(top = 55.dp, end = 48.dp).size(14.dp).clip(CircleShape).background(SoftYellow))
            Box(Modifier.align(Alignment.CenterStart).padding(start = 39.dp, top = 20.dp).size(10.dp).clip(CircleShape).background(SoftGreen))
            Box(
                Modifier.align(Alignment.Center).padding(top = 26.dp).size(190.dp).clip(CircleShape)
                    .background(Brush.verticalGradient(listOf(Color.White.copy(alpha = 0.23f), Color.White.copy(alpha = 0.1f)))),
                contentAlignment = Alignment.Center
            ) {
                Box(Modifier.size(132.dp).clip(CircleShape).background(Color.White), contentAlignment = Alignment.Center) {
                    Icon(icon, contentDescription = product.nome, tint = PurpleMain, modifier = Modifier.size(72.dp))
                }
            }
            Text("SABOR INSPIRADO EM MITOLOGIAS", color = Color.White.copy(alpha = 0.82f), fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.1.sp, modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 28.dp))
        }

        Column(Modifier.fillMaxWidth().padding(horizontal = 23.dp).padding(top = 21.dp, bottom = 30.dp)) {
            Text(product.categoria.uppercase(), color = PurpleMain, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.2.sp)
            Spacer(Modifier.height(6.dp))
            Text(product.nome, color = PurpleDark, fontSize = 26.sp, fontWeight = FontWeight.ExtraBold, modifier = Modifier.semantics { heading() })
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(5) { Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFD3A94E), modifier = Modifier.size(16.dp)) }
                Spacer(Modifier.width(6.dp))
                Text("Uma escolha especial do MYTOS", color = TextMuted, fontSize = 11.sp)
            }
            Spacer(Modifier.height(15.dp))
            Text(product.descricao, color = TextMuted, fontSize = 15.sp, lineHeight = 22.sp)
            Spacer(Modifier.height(20.dp))

            Surface(shape = RoundedCornerShape(22.dp), color = Color.White) {
                Column(Modifier.fillMaxWidth().padding(17.dp)) {
                    Text("A inspiração", color = PurpleDark, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(7.dp))
                    Text(product.historia, color = TextMuted, fontSize = 13.sp, lineHeight = 20.sp)
                }
            }
            Spacer(Modifier.height(13.dp))
            Surface(shape = RoundedCornerShape(22.dp), color = LilacLight) {
                Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(40.dp).clip(RoundedCornerShape(13.dp)).background(SoftGreen), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Coffee, contentDescription = null, tint = PurpleMain, modifier = Modifier.size(21.dp))
                    }
                    Spacer(Modifier.width(11.dp))
                    Column {
                        Text("Feito para sua pausa", color = PurpleDark, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        Text("Consulte a equipe sobre ingredientes e alergênicos.", color = TextMuted, fontSize = 10.sp, lineHeight = 15.sp)
                    }
                }
            }
            Spacer(Modifier.height(21.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text("Preço", color = TextMuted, fontSize = 11.sp)
                    Text(product.preco, color = PurpleMain, fontSize = 23.sp, fontWeight = FontWeight.ExtraBold)
                }
                Button(
                    onClick = { onAddToBag(product) },
                    shape = RoundedCornerShape(17.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PurpleMain, contentColor = Color.White),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 18.dp, vertical = 14.dp)
                ) {
                    Icon(Icons.Default.ShoppingBag, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Adicionar", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InfoScreenPreview() {
    MythosTheme { InfoScreen(productId = 1) }
}
