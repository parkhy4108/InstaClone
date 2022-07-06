package com.devyoung.login.presentation.screen.emailLogin

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.R.string as AppText
import com.devyoung.base.composable.BasicTextButton
import com.devyoung.base.composable.UserNameField
import com.devyoung.base.composable.UserPasswordField

@Composable
fun LoginScreen(
    openAndPopUp: (String, String) -> Unit,
    openScreen: (String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val uiState by viewModel.loginState
    val isValidate by derivedStateOf {
        uiState.userName.isNotBlank() && uiState.userPassword.isNotBlank()
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login", style = TextStyle(fontSize = 40.sp), fontFamily = FontFamily.Serif)

        Spacer(modifier = Modifier.height(25.dp))

        UserNameField(value = uiState.userName, onNewValue = {viewModel.onEvent(LoginEvent.EmailChange(it))}, modifier = Modifier)

        Spacer(modifier = Modifier.height(18.dp))

        UserPasswordField(value = uiState.userPassword, onNewValue = viewModel::onPasswordChange, modifier = Modifier)

        Spacer(modifier = Modifier.height(18.dp))

        BasicTextButton(text = AppText.login , enable = isValidate, modifier = Modifier) {
            viewModel.onLoginClick(openAndPopUp)
        }

        Spacer(modifier = Modifier.height(18.dp))

//        FaceBookLoginButton()

        BasicTextButton(text = AppText.signUp, enable = true, modifier = Modifier) {
            viewModel.onSignUpClick(openScreen)
        }

    }


}






