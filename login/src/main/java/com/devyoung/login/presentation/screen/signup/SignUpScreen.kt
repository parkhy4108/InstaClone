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
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.devyoung.base.R
import java.io.ByteArrayOutputStream


@Composable
fun SignUpScreen(
    navigateBottomBar: (String) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val signUpState by viewModel.signUpState

    val isValidate by derivedStateOf {
        signUpState.userEmail.isNotBlank() && signUpState.userPassword.isNotBlank()
                && signUpState.userNickName.isNotBlank()
    }
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current
    val drawable = ContextCompat.getDrawable(LocalContext.current, R.drawable.ic_empty_user_img)
    val bitmap = drawable!!.toBitmap()
    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos)
    val data = baos.toByteArray()
    Log.d("TAG", "emptyImg = $data")
    Column(
        modifier = Modifier
            .fillMaxSize()
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
                .padding(50.dp, 0.dp)
        ) {
            viewModel.onSignUpClick(
                data,
                navigateBottomBar
            )
        }
    }
}

