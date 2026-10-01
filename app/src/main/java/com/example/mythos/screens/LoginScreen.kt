package com.example.mythos.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
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
private val FieldBg = Color(0xFFFFFEFC)
private val FieldBorder = Color(0xFFE2D8E9)
private val SoftGreen = Color(0xFFE5F0DA)
private val SoftYellow = Color(0xFFFFF0C7)

@Composable
fun LoginScreen(
    @Suppress("UNUSED_PARAMETER") onBackClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onLoginSuccess: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .verticalScroll(rememberScrollState())
            .imePadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Cabeçalho da marca
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(bottomStart = 42.dp, bottomEnd = 42.dp))
                .background(Brush.verticalGradient(listOf(PurpleTop, PurpleDark)))
        ) {
            // Pequenos detalhes suaves para manter a identidade acolhedora.
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 30.dp, top = 34.dp)
                    .size(18.dp)
                    .clip(RoundedCornerShape(50))
                    .background(SoftYellow.copy(alpha = 0.85f))
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 42.dp, bottom = 48.dp)
                    .size(12.dp)
                    .clip(RoundedCornerShape(50))
                    .background(SoftGreen.copy(alpha = 0.85f))
            )
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .statusBarsPadding()
                    .padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                androidx.compose.foundation.Image(
                    painter = painterResource(id = R.drawable.mythos_logo_vector),
                    contentDescription = "Logo MYTOS",
                    modifier = Modifier.size(130.dp),
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.White)
                )
                Text(
                    text = "CAFÉ • SABOR • MITOLOGIA",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.5.sp
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
                .padding(top = 25.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Que bom ter você aqui!",
                color = PurpleDark,
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.semantics { heading() }
            )
            Spacer(Modifier.height(7.dp))
            Text(
                text = "Entre na sua conta e escolha\nsua próxima aventura saborosa.",
                color = TextMuted,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(25.dp))
            LoginTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = null
                },
                placeholder = "E-mail",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email,
                errorMessage = emailError,
                imeAction = ImeAction.Next
            )
            Spacer(Modifier.height(14.dp))
            LoginTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = null
                },
                placeholder = "Senha",
                icon = Icons.Default.Lock,
                password = true,
                errorMessage = passwordError,
                imeAction = ImeAction.Done,
                onImeAction = {
                    validateAndContinue(
                        email = email,
                        password = password,
                        onEmailError = { emailError = it },
                        onPasswordError = { passwordError = it },
                        onSuccess = onLoginSuccess
                    )
                }
            )

            Text(
                text = "Esqueci minha senha",
                color = PurpleMain,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable {
                        Toast.makeText(context, "A recuperação de senha ainda precisa ser conectada.", Toast.LENGTH_SHORT).show()
                    }
                    .padding(top = 10.dp, bottom = 14.dp)
            )

            Button(
                onClick = {
                    validateAndContinue(
                        email = email,
                        password = password,
                        onEmailError = { emailError = it },
                        onPasswordError = { passwordError = it },
                        onSuccess = onLoginSuccess
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PurpleMain,
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp)
            ) {
                Text("ENTRAR", fontSize = 15.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.6.sp)
            }

            Spacer(Modifier.height(23.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Primeira visita?", color = TextMuted, fontSize = 13.sp)
                Spacer(Modifier.size(5.dp))
                OutlinedButton(
                    onClick = onSignUpClick,
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(1.dp, PurpleMain),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = PurpleMain)
                ) {
                    Text("Criar conta", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }

            Spacer(Modifier.height(17.dp))
            InstagramButton(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/bia__e__bel/"))
                    context.startActivity(intent)
                },
                buttonGreen = PurpleMain
            )
        }
    }
}

private fun validateAndContinue(
    email: String,
    password: String,
    onEmailError: (String?) -> Unit,
    onPasswordError: (String?) -> Unit,
    onSuccess: () -> Unit
) {
    val normalizedEmail = email.trim()
    val validEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(normalizedEmail).matches()
    onEmailError(if (normalizedEmail.isBlank()) "Digite seu e-mail." else if (!validEmail) "Confira o formato do e-mail." else null)
    onPasswordError(if (password.isBlank()) "Digite sua senha." else null)
    if (validEmail && password.isNotBlank()) onSuccess()
}

@Composable
fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    password: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    errorMessage: String? = null,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: (() -> Unit)? = null
) {
    var visible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        isError = errorMessage != null,
        label = { Text(placeholder) },
        supportingText = errorMessage?.let { message -> ({ Text(message) }) },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null, tint = PurpleMain)
        },
        trailingIcon = if (password) {
            {
                IconButton(onClick = { visible = !visible }) {
                    Icon(
                        imageVector = if (visible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (visible) "Ocultar senha" else "Mostrar senha",
                        tint = TextMuted
                    )
                }
            }
        } else null,
        visualTransformation = if (password && !visible) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (password) KeyboardType.Password else keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onNext = { if (!password) onImeAction?.invoke() },
            onDone = { onImeAction?.invoke() }
        ),
        shape = RoundedCornerShape(17.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = FieldBg,
            unfocusedContainerColor = FieldBg,
            errorContainerColor = FieldBg,
            focusedBorderColor = PurpleMain,
            unfocusedBorderColor = FieldBorder,
            focusedLabelColor = PurpleMain,
            unfocusedLabelColor = TextMuted,
            focusedTextColor = PurpleDark,
            unfocusedTextColor = PurpleDark,
            cursorColor = PurpleMain
        )
    )
}

@Composable
fun InstagramButton(onClick: () -> Unit, buttonGreen: Color) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        border = BorderStroke(1.dp, FieldBorder),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = buttonGreen)
    ) {
        Text("Instagram  @bia__e__bel", fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    MythosTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Cream) {
            LoginScreen()
        }
    }
}
