package com.devyoung.login.presentation.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import dagger.hilt.android.AndroidEntryPoint

@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState




    var userNameInput by remember {
        mutableStateOf("")
    }
    var userPasswordInput by remember {
        mutableStateOf("")
    }

    val passwordResource: (Boolean) -> Int = {
        if(it) R.drawable.ic_visibility
        else R.drawable.ic_visibility_off
    }

    val showPassword = remember {
        mutableStateOf(false)
    }

    val isValidate by derivedStateOf {
        userNameInput.isNotBlank() &&
        userPasswordInput.isNotBlank()
//                &&userPhoneInput.text.isNotBlank()
    }

//    val phoneValidate by derivedStateOf {
//        userPhoneInput.text.isNotBlank() && userPhoneInput.text.length == 11
//    }

//    val phoneNumValidateImg: (Boolean) -> Int = {
//        if(it) R.drawable.ic_check
//        else R.drawable.ic_not
//    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .align(alignment = Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sign Up",
                style = TextStyle(fontSize = 40.sp),
                fontFamily = FontFamily.Default
            )
            Spacer(modifier = Modifier.height(25.dp))

            userNameField(
                value = uiState.userName,
                onNewValue = viewModel::onUserNameChange,
                modifier = Modifier
            )

            TextField(
                value = userNameInput,
                onValueChange = { newValue -> userNameInput = newValue },
                label = { Text("이메일", fontSize = 15.sp)  },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )
            Spacer(modifier = Modifier.height(18.dp))

            TextField(
                value = userPasswordInput,
                onValueChange = { newValue -> userPasswordInput = newValue },
                label = { Text("비밀번호", fontSize = 15.sp) },
                placeholder = { Text(text = "비밀번호를 입력해주세요.",  fontSize = 15.sp )},
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(
                        onClick = { showPassword.value = !showPassword.value }
                    ) {
                        Icon(painter = painterResource(id = passwordResource(showPassword.value)), contentDescription = null)
                    }
                },
                visualTransformation = if(showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                colors = TextFieldDefaults.textFieldColors(backgroundColor =Color.White)
            )

            Spacer(modifier = Modifier.height(18.dp))


//            TextField(
//                value = userPhoneInput,
//                onValueChange = { newValue -> userPhoneInput = newValue },
//                label = { Text("01012341234", fontSize = 15.sp)  },
//                colors = TextFieldDefaults.textFieldColors(backgroundColor =Color.White),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
//                trailingIcon = {
//                    Icon(
//                        painter = painterResource(id = phoneNumValidateImg(phoneValidate)),
//                        contentDescription = "phoneCheck"
//                    )
//                }
//            )
//            Spacer(modifier = Modifier.height(18.dp))

        }
        Box(
            modifier = Modifier
                .padding(40.dp)
                .align(alignment = Alignment.BottomCenter)
        ){
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1A2FCD)),
                enabled = isValidate,
                onClick = {viewModel.signUpClick(userNameInput,userPasswordInput) }
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 15.sp,
                    style = TextStyle(color = Color.White)
                )
            }
        }

    }


}

@Composable
fun userNameField(value: String, onNewValue: (String)-> Unit, modifier: Modifier){
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = {onNewValue(it)},
        singleLine = true
    )

}