package com.devyoung.search.presentation.user

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.composable.CircularIndicatorProgressBar
import com.devyoung.base.composable.loadPicture
import com.devyoung.search.presentation.composable.UserSection

@Composable
fun UserScreen(
    id: String?,
    popUpScreen: () -> Unit,
    viewModel: UserViewModel = hiltViewModel()
) {
    val userState by viewModel.uiState
    val imageWidth = LocalConfiguration.current.screenWidthDp
    val imageHeight = imageWidth / 3

    val imageList = userState.post

    LaunchedEffect(Unit){
        viewModel.initialize(id!!)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        UserTopBar(
            text = userState.user?.userNickName,
            backgroundColor = Color.White,
            elevation = 0.dp,
            onClick = popUpScreen
        )
        Spacer(modifier = Modifier.height(1.dp))

        Box(modifier = Modifier){
            LazyVerticalGrid(columns = GridCells.Fixed(3)){
                item(span = { GridItemSpan(3) }) {
                    userState.user?.let {
                        UserSection(
                            user = it,
                            state = userState,
                            onFollowButtonClick = {
                                if (id != null) {
                                    viewModel.onFollowButtonClicked(id)
                                }
                            }
                        )
                    }
                }
                if (imageList != null){
                    items(imageList) { image ->
                        val img = loadPicture(uri = image.toUri(), defaultImage = null).value
                        Log.d(TAG, "postItem: $image")
                        Card(
                            modifier = Modifier
                                .height(imageHeight.dp)
                        ) {
                            if (img != null) {
                                Image(
                                    bitmap = img.asImageBitmap(),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
            CircularIndicatorProgressBar(isDisplayed = userState.screenLoading)
        }


    }

}


