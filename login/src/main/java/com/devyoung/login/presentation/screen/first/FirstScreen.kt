package com.devyoung.login.presentation.screen.first

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FirstScreen(
    openAndPopUp: (String, String)-> Unit,
    viewModel: FirstViewModel = hiltViewModel()
) {
    viewModel.appStart(openAndPopUp)
}