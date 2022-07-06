package com.devyoung.feeds.presentation.screen.post

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skydoves.landscapist.glide.GlideImage
import com.devyoung.feeds.R.drawable as AppImg

@Composable
fun PostScreen(
    popUpScreen: () -> Unit,
    viewModel: PostViewModel = hiltViewModel()
) {

    val postState by viewModel.postState

    val selectImageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            postState.imageUrl = it.toString()
            viewModel.onImageChange(it.toString())
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            modifier = Modifier,
            backgroundColor = Color.White,
            elevation = 3.dp
        ){
            Text(
                modifier = Modifier,
                text = "Post")
            Button(
                onClick = { selectImageLauncher.launch("image/*") }
            ) {
                Image(
                    painter = painterResource(id =AppImg.ic_add_photo),
                    contentDescription = null
                )
            }
            Button(
                onClick = { viewModel.onSavePostClick(popUpScreen) }
            ) {
                Image(
                    painter = painterResource(id = AppImg.ic_okay),
                    contentDescription = null
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxHeight(0.6f)
        ){
            GlideImage(imageModel = postState.imageUrl)
        }
        Box(
            modifier = Modifier
                .fillMaxHeight(0.4f)
        ){
            Column(
                modifier = Modifier,
            ) {
                Text(text = "문구")
                TextField(value = postState.comments, onValueChange = viewModel::onCommentsChange  )
            }
        }
    }
}