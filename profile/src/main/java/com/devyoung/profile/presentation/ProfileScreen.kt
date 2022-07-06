package com.devyoung.profile.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.profile.data.data_source.User
import com.devyoung.profile.presentation.composable.*

@Composable
fun ProfileScreen(
    restartApp: (String) -> Unit,
//    openScreen: (String)-> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState
    val imageWidth = LocalConfiguration.current.screenWidthDp

    val mockUser = User(
        nickName = "HaY",
        userImage = "https://randomuser.me/api/portraits/men/51.jpg",
        postNum = 50,
        follower = 20,
        following = 30,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ProfileTopBar(text = mockUser.nickName, backgroundColor = Color.White, elevation = 0.dp) {
            viewModel.userLogOut(
                restartApp
            )
        }
        GridSection(user = mockUser, width = imageWidth)
    }
}



