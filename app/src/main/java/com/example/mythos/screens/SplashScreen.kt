package com.example.mythos.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mythos.R
import com.example.mythos.ui.theme.MythosTheme
import androidx.compose.ui.graphics.ColorFilter

// Paleta MYTOS
private val PurpleTop = Color(0xFF3A1A63)
private val PurpleMid = Color(0xFF2A1248)
private val PurpleBottom = Color(0xFF1B0B33)
private val Cream = Color(0xFFFBF3E4)
private val ProgressPurple = Color(0xFF9B6DDB)

@Composable
fun SplashScreen(
    onFinished: () -> Unit,
    durationMillis: Int = 2500
) {
    val progress = remember { Animatable(0f) }
    val currentOnFinished = rememberUpdatedState(onFinished)

    // Anima a barra e navega quando terminar
    LaunchedEffect(Unit) {
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing)
        )
        currentOnFinished.value()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(listOf(PurpleTop, PurpleMid, PurpleBottom))
            )
    ) {
        // Montanhas ao fundo (decorativo)
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            val back = Path().apply {
                moveTo(0f, h)
                lineTo(0f, h * 0.78f)
                lineTo(w * 0.22f, h * 0.68f)
                lineTo(w * 0.40f, h * 0.76f)
                lineTo(w * 0.62f, h * 0.64f)
                lineTo(w * 0.82f, h * 0.74f)
                lineTo(w, h * 0.66f)
                lineTo(w, h)
                close()
            }
            drawPath(back, Color.White.copy(alpha = 0.05f))

            val front = Path().apply {
                moveTo(0f, h)
                lineTo(0f, h * 0.86f)
                lineTo(w * 0.30f, h * 0.78f)
                lineTo(w * 0.55f, h * 0.87f)
                lineTo(w * 0.78f, h * 0.80f)
                lineTo(w, h * 0.88f)
                lineTo(w, h)
                close()
            }
            drawPath(front, Color.White.copy(alpha = 0.07f))
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            // Logo MYTOS com o Iago
            Image(
                painter = painterResource(id = R.drawable.mythos_logo_vector),
                contentDescription = "MYTOS, logo com Iago, o lobo-guará mascote do café",
                modifier = Modifier.size(260.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Café, sabor e lendas\nem cada gole.",
                color = Cream,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 26.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            // Barra de progresso + "Carregando..."
            Column(
                modifier = Modifier
                    .semantics(mergeDescendants = true) {
                        contentDescription = "Carregando o aplicativo"
                        liveRegion = LiveRegionMode.Polite
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearProgressIndicator(
                    progress = { progress.value },
                    modifier = Modifier
                        .width(150.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(50)),
                    color = ProgressPurple,
                    trackColor = Color.White.copy(alpha = 0.18f),
                    strokeCap = androidx.compose.ui.graphics.StrokeCap.Round,
                    gapSize = 0.dp,
                    drawStopIndicator = {}
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Carregando...",
                    color = Cream.copy(alpha = 0.7f),
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    MythosTheme {
        SplashScreen(onFinished = {})
    }
}