package com.example.mythos.screens

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mythos.R
import com.example.mythos.ui.theme.MythosTheme

private val Cream = Color(0xFFFBF7EF)
private val PurpleTop = Color(0xFF61388A)
private val PurpleMain = Color(0xFF5B2D91)
private val PurpleDark = Color(0xFF321B4B)
private val TextMuted = Color(0xFF756B7E)
private val SoftGreen = Color(0xFFE5F0DA)
private val SoftYellow = Color(0xFFFFF0C7)

/** Preferência de acessibilidade usada pelo app MYTOS. */
object AcessibilidadePrefs {
    private const val ARQUIVO = "mytos_prefs"
    private const val CHAVE = "acessibilidade_ativa"

    fun salvar(context: Context, ativa: Boolean) {
        context.getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(CHAVE, ativa)
            .apply()
    }

    fun estaAtiva(context: Context): Boolean =
        context.getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE)
            .getBoolean(CHAVE, false)
}

@Composable
fun AcessibilidadeScreen(
    onAtivar: () -> Unit = {},
    onContinuar: () -> Unit = {}
) {
    val context = LocalContext.current
    var preferenciaAtiva by remember { mutableStateOf(AcessibilidadePrefs.estaAtiva(context)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier.fillMaxWidth().height(235.dp)
                .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                .background(Brush.verticalGradient(listOf(PurpleTop, PurpleDark))),
        ) {
            // A moldura corta a parte inferior da ilustração, deixando Bast maior no canto.
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 190.dp, height = 205.dp)
                        .clipToBounds()
                ) {
                    Image(
                        painter = painterResource(R.drawable.bast_acessibilidade),
                        contentDescription = "Bast, mascote do MYTOS, segurando uma placa de acessibilidade",
                        modifier = Modifier
                            .size(width = 255.dp, height = 255.dp)
                            .offset(x = (-28).dp, y = 38.dp)
                    )
                }
                Text(
                    text = "MYTOS\nPARA TODOS",
                    color = Color.White,
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.2.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f).padding(start = 5.dp, end = 16.dp)
                )
            }
        }

        Column(
            Modifier.fillMaxWidth().padding(horizontal = 25.dp).padding(top = 23.dp, bottom = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Como prefere usar o MYTOS?",
                color = PurpleDark,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.semantics { heading() }
            )
            Spacer(Modifier.height(7.dp))
            Text(
                "Você pode escolher recursos que tornam sua experiência mais confortável.",
                color = TextMuted,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(21.dp))

            Surface(shape = RoundedCornerShape(23.dp), color = Color.White, border = BorderStroke(1.dp, Color(0xFFE2D8E9)), shadowElevation = 2.dp) {
                Column(Modifier.fillMaxWidth().padding(17.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(50.dp).clip(RoundedCornerShape(16.dp)).background(SoftGreen), contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.RecordVoiceOver, contentDescription = null, tint = PurpleMain, modifier = Modifier.size(27.dp))
                        }
                        Spacer(Modifier.size(13.dp))
                        Column {
                            Text("Leitura de tela", color = PurpleDark, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text("Use TalkBack para ouvir os elementos do app.", color = TextMuted, fontSize = 12.sp, lineHeight = 17.sp)
                        }
                    }
                    Spacer(Modifier.height(14.dp))
                    OutlinedButton(
                        onClick = {
                            context.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                        },
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        shape = RoundedCornerShape(15.dp),
                        border = BorderStroke(1.dp, PurpleMain),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = PurpleMain)
                    ) {
                        Icon(Icons.Default.Settings, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.size(8.dp))
                        Text("ABRIR CONFIGURAÇÕES DO ANDROID", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(Modifier.height(9.dp))
                    Text(
                        "O TalkBack é ativado nas configurações de acessibilidade do aparelho. Esta tela não altera essa configuração do Android.",
                        color = TextMuted,
                        fontSize = 11.sp,
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(Modifier.height(15.dp))
            Surface(shape = RoundedCornerShape(23.dp), color = Color.White, border = BorderStroke(1.dp, Color(0xFFE2D8E9)), shadowElevation = 2.dp) {
                Column(Modifier.fillMaxWidth().padding(17.dp)) {
                    Text("Recursos acessíveis do MYTOS", color = PurpleDark, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(5.dp))
                    Text("Salve sua preferência para que o app possa adaptar a experiência.", color = TextMuted, fontSize = 12.sp, lineHeight = 17.sp)
                    Spacer(Modifier.height(14.dp))
                    Button(
                        onClick = {
                            preferenciaAtiva = true
                            AcessibilidadePrefs.salvar(context, true)
                            onAtivar()
                        },
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PurpleMain, contentColor = Color.White)
                    ) {
                        Text(if (preferenciaAtiva) "PREFERÊNCIA ATIVADA" else "ATIVAR RECURSOS", fontSize = 13.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.8.sp)
                    }
                    Spacer(Modifier.height(8.dp))
                    OutlinedButton(
                        onClick = {
                            preferenciaAtiva = false
                            AcessibilidadePrefs.salvar(context, false)
                            onContinuar()
                        },
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, PurpleMain),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = PurpleMain)
                    ) {
                        Text("CONTINUAR", fontSize = 13.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                        Spacer(Modifier.size(7.dp))
                        Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(17.dp))
                    }
                }
            }
            Spacer(Modifier.height(17.dp))
            Text("Você pode mudar essa preferência depois no seu perfil.", color = TextMuted, fontSize = 12.sp, textAlign = TextAlign.Center)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AcessibilidadeScreenPreview() {
    MythosTheme { AcessibilidadeScreen() }
}
