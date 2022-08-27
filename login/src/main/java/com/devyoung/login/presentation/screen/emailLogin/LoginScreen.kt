package com.devyoung.login.presentation.screen.emailLogin

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.layout.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import com.devyoung.login.presentation.screen.composable.UserPasswordField
import com.devyoung.login.presentation.screen.composable.BasicTextButton
import com.devyoung.login.presentation.screen.composable.UserNameField
import com.devyoung.base.R.string as AppText


@Composable
fun LoginScreen(
    navigateBottomBar: (String) -> Unit,
    openScreen: (String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val uiState by viewModel.loginState

    val isValidate by derivedStateOf {
        uiState.userName.isNotBlank() && uiState.userPassword.isNotBlank()
    }
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Login",
            style = TextStyle(fontSize = 40.sp),
            fontFamily = FontFamily.Serif
        )

        Spacer(modifier = Modifier.height(25.dp))

        UserNameField(
            value = uiState.userName,
            onNewValue = viewModel::onEmailChange,
            modifier = Modifier
                .focusRequester(focusRequester)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }
        )

        Spacer(modifier = Modifier.height(18.dp))



        UserPasswordField(
            value = uiState.userPassword,
            onNewValue = viewModel::onPasswordChange,
            modifier = Modifier
                .focusRequester(focusRequester)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }
        )

        Spacer(modifier = Modifier.height(18.dp))

        BasicTextButton(
            text = AppText.login,
            enable = isValidate,
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp, 0.dp)
        ) {
            viewModel.onLoginClick(navigateBottomBar)
        }

        Spacer(modifier = Modifier.height(18.dp))

        BasicTextButton(
            text = AppText.signUp,
            enable = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp, 0.dp)
        ) {
            viewModel.onSignUpClick(openScreen)
        }

    }
}

fun Modifier.addFocusCleaner(focusManager: FocusManager, doOnClear: () -> Unit = {}): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            doOnClear()
            focusManager.clearFocus()
        })
    }
}





