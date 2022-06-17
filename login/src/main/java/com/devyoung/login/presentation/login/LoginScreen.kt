package com.devyoung.login.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.devyoung.base.NavItems
import com.devyoung.login.R

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    var userNameInput by remember { mutableStateOf("") }

    var userPasswordInput by remember { mutableStateOf("") }

    val passwordResource: (Boolean) -> Int = {
        if(it) R.drawable.ic_visibility
        else R.drawable.ic_visibility_off
    }

    val showPassword = remember {
        mutableStateOf(false)
    }

    val isValidate by derivedStateOf {
        userNameInput.isNotBlank() && userPasswordInput.isNotBlank()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .padding(20.dp, 20.dp, 20.dp, 50.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login",
                style = TextStyle(fontSize = 40.sp),
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(25.dp))

            TextField(
                value = userNameInput,
                onValueChange = { newValue -> userNameInput = newValue },
                label = { Text("이메일 또는 전화번호", fontSize = 15.sp)  },
                colors = TextFieldDefaults.textFieldColors(backgroundColor =Color.White),
            )


            Spacer(modifier = Modifier.height(18.dp))


            TextField(
                value = userPasswordInput,
                onValueChange = { newValue -> userPasswordInput = newValue },
                label = { Text("비밀번호", fontSize = 15.sp) },
                placeholder = { Text(text = "비밀번호를 입력해주세요.",  fontSize = 15.sp )},
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(backgroundColor =Color.White),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(
                        onClick = { showPassword.value = !showPassword.value }
                    ) {
                        Icon(painter = painterResource(id = passwordResource(showPassword.value)), contentDescription = null)
                    }
                },
                visualTransformation = if(showPassword.value) VisualTransformation.None else PasswordVisualTransformation()
            )


            Spacer(modifier = Modifier.height(18.dp))


            Box(
                modifier = Modifier
                    .padding(40.dp,0.dp)
            ){
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1A2FCD)),
                    enabled = isValidate,
                    onClick = {
                        val user = viewModel.loginClick(userNameInput,userPasswordInput)
                        navController.navigate(NavItems.Home.route)}
                ) {
                    Text(
                        text = "Login",
                        fontSize = 15.sp,
                        style = TextStyle(color = Color.White)
                    )
                }
            }


            Spacer(modifier = Modifier.height(18.dp))


            ClickableText(
                text = AnnotatedString("페이스북 로그인"),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default
                ),
                onClick = { navController.navigate(NavItems.FaceBookLogin.route) }
            )
        }


        ClickableText(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp)
            ,
            text = AnnotatedString("Sign up here"),
            onClick = { navController.navigate(NavItems.SignUp.route)},
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily.Default,
                color = Color(0xFF64B5F6)
            )
        )
    }


}






