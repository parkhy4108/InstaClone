package com.devyoung.login.presentation.screen.facebookLogin

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.login.presentation.screen.emailLogin.LoginViewModel

@Composable
fun FaceBookLoginScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

}