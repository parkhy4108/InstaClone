package com.devyoung.search.presentation.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.composable.CircularIndicatorProgressBar
import com.devyoung.base.composable.ImgLoad
import com.devyoung.search.presentation.composable.UserSection
import com.devyoung.search.presentation.composable.UserTopBar

@Composable
fun UserScreen(
    id: String?,
    popUpScreen: () -> Unit,
    viewModel: UserViewModel = hiltViewModel()
) {
    val userState by viewModel.userState
    val imageWidth = LocalConfiguration.current.screenWidthDp
    val imageHeight = imageWidth / 3

    LaunchedEffect(Unit){
        if (id != null) {
            viewModel.initialize(id)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        userState.user?.userNickName?.let {
            UserTopBar(
                text = it,
                onClick = popUpScreen,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 0.dp)
                    .background(color = Color.White)
            )
        }

        Spacer(modifier = Modifier.height(1.dp))

        Box(modifier = Modifier){
            LazyVerticalGrid(columns = GridCells.Fixed(3)){
                item(span = { GridItemSpan(3) }) {
                    userState.user?.let {
                        UserSection(
                            user = it,
                            state = userState,
                            onFollowButtonClick = { viewModel.onFollowButtonClicked(id.toString()) },
                            onDialogCancel = { viewModel.onDialogCancel() },
                            onDialogConfirmClick = { viewModel.onDialogConfirmClicked(id.toString()) }
                        )
                    }
                }
                items(userState.post) { image ->
                    ImgLoad(
                        imgUrl = image.toUri(),
                        modifier = Modifier
                            .height(imageHeight.dp)
                    )
                }
            }
            CircularIndicatorProgressBar(isDisplayed = userState.screenLoading)
        }
    }
}


