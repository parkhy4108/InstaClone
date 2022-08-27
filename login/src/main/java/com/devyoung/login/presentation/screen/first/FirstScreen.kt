package com.devyoung.login.presentation.screen.first

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FirstScreen(
    openAndPopUp: (String, String) -> Unit,
    navigateBottomBar: (String) -> Unit,
    viewModel: FirstViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit){
        viewModel.appStart(openAndPopUp,navigateBottomBar)
    }
}