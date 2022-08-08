package com.devyoung.login.presentation.screen.signup

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.login.presentation.screen.composable.BasicTextButton
import com.devyoung.login.presentation.screen.composable.UserNameField
import com.devyoung.login.presentation.screen.composable.UserNickNameField
import com.devyoung.login.presentation.screen.composable.UserPasswordField
import com.devyoung.login.presentation.screen.emailLogin.addFocusCleaner
import com.devyoung.base.R.string as AppText

@Composable
fun SignUpScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {

    Log.d(TAG, "SignUpScreen 진입")

    val signUpState by viewModel.signUpState

    val isValidate by derivedStateOf {
        signUpState.userEmail.isNotBlank() && signUpState.userPassword.isNotBlank()
                && signUpState.userNickName.isNotBlank()
    }
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .addFocusCleaner(focusManager),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier,
            text = stringResource(id = AppText.signUp),
            style = TextStyle(fontSize = 40.sp),
            fontFamily = FontFamily.Default
        )

        Spacer(modifier = Modifier.height(25.dp))

        UserNameField(
            signUpState.userEmail,
            viewModel::onEmailChange,
            Modifier
                .focusRequester(focusRequester)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }
        )

        Spacer(modifier = Modifier.height(18.dp))

        UserPasswordField(
            signUpState.userPassword,
            viewModel::onPasswordChange,
            Modifier
                .focusRequester(focusRequester)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }
        )

        Spacer(modifier = Modifier.height(18.dp))

        UserNickNameField(
            signUpState.userNickName,
            viewModel::onNickNameChange,
            Modifier
                .focusRequester(focusRequester)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }
        )

        Spacer(modifier = Modifier.height(18.dp))

        BasicTextButton(
            text = AppText.signUp,
            enable = isValidate,
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp, 0.dp)
        ) {
            viewModel.onSignUpClick(openAndPopUp)
        }
    }


}

