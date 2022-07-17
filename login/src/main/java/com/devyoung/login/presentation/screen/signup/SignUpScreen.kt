package com.devyoung.login.presentation.screen.signup

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.composable.BasicTextButton
import com.devyoung.base.composable.UserNameField
import com.devyoung.base.composable.UserNickNameField
import com.devyoung.base.composable.UserPasswordField
import com.devyoung.base.R.string as AppText

@Composable
fun SignUpScreen(
    openAndPopUp: (String)-> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {

    Log.d(TAG, "SignUpScreen 진입")

    val signUpState by viewModel.signUpState

    val isValidate by derivedStateOf {
        signUpState.userEmail.isNotBlank() && signUpState.userPassword.isNotBlank()
                &&signUpState.userNickName.isNotBlank()
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(modifier = Modifier, text = stringResource(id = AppText.signUp), style = TextStyle(fontSize = 40.sp), fontFamily = FontFamily.Default)

        Spacer(modifier = Modifier.height(25.dp))

        UserNameField(signUpState.userEmail, viewModel::onEmailChange, Modifier)

        Spacer(modifier = Modifier.height(18.dp))

        UserPasswordField(signUpState.userPassword, viewModel::onPasswordChange, Modifier)

        Spacer(modifier = Modifier.height(18.dp))

        UserNickNameField(signUpState.userNickName, viewModel::onNickNameChange , Modifier)

        Spacer(modifier = Modifier.height(18.dp))

        BasicTextButton(text =AppText.signUp, enable = isValidate, modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp, 0.dp)) {
            viewModel.onSignUpClick(openAndPopUp)
        }
    }






}

