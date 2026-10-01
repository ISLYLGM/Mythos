package com.example.mythos.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
private val FieldBg = Color(0xFFFFFEFC)
private val FieldBorder = Color(0xFFE2D8E9)
private val SoftGreen = Color(0xFFE5F0DA)
private val SoftYellow = Color(0xFFFFF0C7)

@Composable
fun CadastroScreen(
    onBackClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onCadastroSuccess: () -> Unit = {}
) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var nomeError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var senhaError by remember { mutableStateOf<String?>(null) }
    var confirmarError by remember { mutableStateOf<String?>(null) }
    var senhaVisivel by remember { mutableStateOf(false) }
    var confirmarVisivel by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .verticalScroll(rememberScrollState())
            .imePadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(bottomStart = 42.dp, bottomEnd = 42.dp))
                .background(Brush.verticalGradient(listOf(PurpleTop, PurpleDark)))
        ) {
            Box(Modifier.align(Alignment.TopStart).padding(start = 30.dp, top = 34.dp).size(17.dp).clip(RoundedCornerShape(50)).background(SoftYellow))
            Box(Modifier.align(Alignment.BottomEnd).padding(end = 40.dp, bottom = 42.dp).size(12.dp).clip(RoundedCornerShape(50)).background(SoftGreen))
            androidx.compose.foundation.Image(
                painter = painterResource(R.drawable.mika),
                contentDescription = "Mika, mascote do MYTOS, segurando uma ficha de cadastro",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .size(width = 260.dp, height = 390.dp)
                    .size(width = 400.dp, height = 600.dp)
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 28.dp).padding(top = 22.dp, bottom = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Vamos começar sua jornada!",
                color = PurpleDark,
                fontSize = 23.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.semantics { heading() }
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "Crie sua conta para descobrir\nsabores inspirados em mitologias.",
                color = TextMuted,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(22.dp))

            CadastroTextField(nome, { nome = it; nomeError = null }, "Seu nome", Icons.Default.Person, errorMessage = nomeError, imeAction = ImeAction.Next)
            Spacer(Modifier.height(12.dp))
            CadastroTextField(email, { email = it; emailError = null }, "E-mail", Icons.Default.Email, keyboardType = KeyboardType.Email, errorMessage = emailError, imeAction = ImeAction.Next)
            Spacer(Modifier.height(12.dp))
            CadastroTextField(senha, { senha = it; senhaError = null }, "Senha (mínimo 6 caracteres)", Icons.Default.Lock, password = true, passwordVisible = senhaVisivel, onPasswordVisibilityChange = { senhaVisivel = !senhaVisivel }, keyboardType = KeyboardType.Password, errorMessage = senhaError, imeAction = ImeAction.Next)
            Spacer(Modifier.height(12.dp))
            CadastroTextField(confirmarSenha, { confirmarSenha = it; confirmarError = null }, "Confirme sua senha", Icons.Default.Lock, password = true, passwordVisible = confirmarVisivel, onPasswordVisibilityChange = { confirmarVisivel = !confirmarVisivel }, keyboardType = KeyboardType.Password, errorMessage = confirmarError, imeAction = ImeAction.Done, onImeAction = {
                validarCadastro(nome, email, senha, confirmarSenha, { nomeError = it }, { emailError = it }, { senhaError = it }, { confirmarError = it }, onCadastroSuccess)
            })

            Spacer(Modifier.height(20.dp))
            Button(
                onClick = {
                    validarCadastro(nome, email, senha, confirmarSenha, { nomeError = it }, { emailError = it }, { senhaError = it }, { confirmarError = it }, onCadastroSuccess)
                },
                modifier = Modifier.fillMaxWidth().height(54.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PurpleMain, contentColor = Color.White),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp)
            ) {
                Text("CRIAR CONTA", fontSize = 15.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.5.sp)
            }
            Spacer(Modifier.height(19.dp))
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Text("Já tem uma conta?", color = TextMuted, fontSize = 13.sp)
                Spacer(Modifier.size(5.dp))
                Text(
                    "Entrar",
                    color = PurpleMain,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onLoginClick() }.padding(4.dp)
                )
            }
        }
    }
}

private fun validarCadastro(
    nome: String,
    email: String,
    senha: String,
    confirmarSenha: String,
    onNomeError: (String?) -> Unit,
    onEmailError: (String?) -> Unit,
    onSenhaError: (String?) -> Unit,
    onConfirmarError: (String?) -> Unit,
    onSuccess: () -> Unit
) {
    val nomeValido = nome.trim().isNotEmpty()
    val emailValido = android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    val senhaValida = senha.length >= 6
    val confirmacaoValida = senha == confirmarSenha && confirmarSenha.isNotEmpty()
    onNomeError(if (nomeValido) null else "Digite seu nome.")
    onEmailError(if (email.isBlank()) "Digite seu e-mail." else if (!emailValido) "Confira o formato do e-mail." else null)
    onSenhaError(if (senha.isBlank()) "Digite uma senha." else if (!senhaValida) "Use pelo menos 6 caracteres." else null)
    onConfirmarError(if (confirmarSenha.isBlank()) "Confirme sua senha." else if (!confirmacaoValida) "As senhas não coincidem." else null)
    if (nomeValido && emailValido && senhaValida && confirmacaoValida) onSuccess()
}

@Composable
fun CadastroTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    password: Boolean = false,
    passwordVisible: Boolean = false,
    onPasswordVisibilityChange: () -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    errorMessage: String? = null,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        isError = errorMessage != null,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = KeyboardActions(onDone = { onImeAction?.invoke() }),
        label = { Text(placeholder) },
        supportingText = errorMessage?.let { message -> ({ Text(message) }) },
        leadingIcon = { Icon(icon, contentDescription = null, tint = PurpleMain) },
        trailingIcon = if (password) {
            {
                IconButton(onClick = onPasswordVisibilityChange) {
                    Icon(
                        if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (passwordVisible) "Ocultar senha" else "Mostrar senha",
                        tint = PurpleMain
                    )
                }
            }
        } else null,
        visualTransformation = if (password && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CadastroScreenPreview() {
    MythosTheme { CadastroScreen() }
}