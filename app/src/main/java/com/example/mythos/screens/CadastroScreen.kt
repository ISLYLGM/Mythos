package com.example.mythos.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.mythos.R


@Composable
fun CadastroScreen(
    onBackClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onCadastroSuccess: () -> Unit = {}
) {

    var nome by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var senha by remember {
        mutableStateOf("")
    }

    var confirmarSenha by remember {
        mutableStateOf("")
    }


    val darkGreen = Color(0xFF006B45)
    val buttonGreen = Color(0xFF009F47)
    val lightBackground = Color(0xFFF7F7F5)
    val white = Color.White
    val textGray = Color(0xFF8A8A8A)
    val lineColor = Color(0xFFE5E5E5)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightBackground)
    ) {

        /*
         * CONTEÚDO PRINCIPAL
         */
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(22.dp)
                )
        ) {

            /*
             * FUNDO VERDE COM DIAGONAL
             */
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            ) {

                val largura = size.width
                val altura = size.height

                val path = Path().apply {

                    moveTo(0f, 0f)

                    lineTo(
                        largura,
                        0f
                    )

                    lineTo(
                        largura,
                        altura * 0.78f
                    )

                    lineTo(
                        0f,
                        altura
                    )

                    close()
                }

                drawPath(
                    path = path,
                    color = darkGreen
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .padding(horizontal = 16.dp),

                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                /*
                 * BOTÃO VOLTAR
                 */
                IconButton(
                    onClick = {
                        onBackClick()
                    },

                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 8.dp)
                ) {

                    Icon(
                        imageVector =
                            Icons.AutoMirrored.Filled.ArrowBack,

                        contentDescription =
                            "Voltar",

                        tint = white,

                        modifier = Modifier.size(30.dp)
                    )
                }


                Spacer(
                    modifier = Modifier.height(10.dp)
                )


                /*
                 * LOGO
                 */
                Image(
                    painter = painterResource(
                        id = R.drawable.mythos_logo_vector
                    ),

                    contentDescription =
                        "Logo do Mythos",

                    modifier = Modifier.size(105.dp)
                )


                Spacer(
                    modifier = Modifier.height(4.dp)
                )


                /*
                 * TÍTULO
                 */
                Text(
                    text = "Crie sua conta",

                    color = white,

                    fontSize = 29.sp
                )


                Spacer(
                    modifier = Modifier.height(4.dp)
                )


                /*
                 * SUBTÍTULO
                 */
                Text(
                    text = "Cadastre-se para começar",

                    color = white.copy(
                        alpha = 0.85f
                    ),

                    fontSize = 14.sp
                )


                Spacer(
                    modifier = Modifier.height(35.dp)
                )


                /*
                 * CARD
                 */
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(18.dp)
                        )
                        .background(white)
                        .padding(
                            horizontal = 20.dp,
                            vertical = 20.dp
                        )
                ) {

                    /*
                     * NOME
                     */
                    CadastroTextField(
                        value = nome,

                        onValueChange = {
                            nome = it
                        },

                        placeholder = "Nome completo",

                        icon = Icons.Default.Person,

                        lineColor = lineColor,

                        textGray = textGray
                    )


                    Spacer(
                        modifier = Modifier.height(18.dp)
                    )


                    /*
                     * E-MAIL
                     */
                    CadastroTextField(
                        value = email,

                        onValueChange = {
                            email = it
                        },

                        placeholder = "E-mail",

                        icon = Icons.Default.Email,

                        lineColor = lineColor,

                        textGray = textGray,

                        keyboardType =
                            KeyboardType.Email
                    )


                    Spacer(
                        modifier = Modifier.height(18.dp)
                    )


                    /*
                     * SENHA
                     */
                    CadastroTextField(
                        value = senha,

                        onValueChange = {
                            senha = it
                        },

                        placeholder = "Senha",

                        icon = Icons.Default.Lock,

                        lineColor = lineColor,

                        textGray = textGray,

                        password = true
                    )


                    Spacer(
                        modifier = Modifier.height(18.dp)
                    )


                    /*
                     * CONFIRMAR SENHA
                     */
                    CadastroTextField(
                        value = confirmarSenha,

                        onValueChange = {
                            confirmarSenha = it
                        },

                        placeholder = "Confirmar senha",

                        icon = Icons.Default.Lock,

                        lineColor = lineColor,

                        textGray = textGray,

                        password = true
                    )


                    Spacer(
                        modifier = Modifier.height(25.dp)
                    )


                    /*
                     * BOTÃO CADASTRAR
                     */
                    Button(
                        onClick = {

                            onCadastroSuccess()

                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),

                        shape = RoundedCornerShape(28.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonGreen
                        )
                    ) {

                        Text(
                            text = "Cadastrar",

                            color = white,

                            fontSize = 17.sp
                        )
                    }
                }


                Spacer(
                    modifier = Modifier.height(20.dp)
                )


                /*
                 * VOLTAR PARA LOGIN
                 */
                Row(
                    horizontalArrangement =
                        Arrangement.Center,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(
                        text =
                            "Já possui uma conta? ",

                        color = textGray,

                        fontSize = 13.sp
                    )


                    Text(
                        text = "Entrar",

                        color = buttonGreen,

                        fontSize = 13.sp,

                        modifier = Modifier.clickable {

                            onLoginClick()

                        }
                    )
                }


                Spacer(
                    modifier = Modifier.height(25.dp)
                )
            }
        }
    }
}


/*
 * CAMPO DE CADASTRO
 */
@Composable
fun CadastroTextField(
    value: String,

    onValueChange: (String) -> Unit,

    placeholder: String,

    icon: ImageVector,

    lineColor: Color,

    textGray: Color,

    password: Boolean = false,

    keyboardType: KeyboardType =
        KeyboardType.Text
) {

    BasicTextField(

        value = value,

        onValueChange = onValueChange,

        singleLine = true,

        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),

        visualTransformation =
            if (password) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },

        textStyle = TextStyle(
            color = Color(0xFF333333),

            fontSize = 15.sp
        ),

        modifier = Modifier.fillMaxWidth(),

        decorationBox = { innerTextField ->

            Column {

                Row(
                    modifier = Modifier.fillMaxWidth(),

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = icon,

                        contentDescription = null,

                        tint = textGray,

                        modifier = Modifier.size(20.dp)
                    )


                    Spacer(
                        modifier = Modifier.size(10.dp)
                    )


                    Box(
                        modifier = Modifier.weight(1f)
                    ) {

                        if (value.isEmpty()) {

                            Text(
                                text = placeholder,

                                color = textGray,

                                fontSize = 15.sp
                            )
                        }

                        innerTextField()
                    }
                }


                Spacer(
                    modifier = Modifier.height(10.dp)
                )


                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                ) {

                    drawLine(
                        color = lineColor,

                        start = Offset(
                            0f,
                            0f
                        ),

                        end = Offset(
                            size.width,
                            0f
                        ),

                        strokeWidth = 1f
                    )
                }
            }
        }
    )
}