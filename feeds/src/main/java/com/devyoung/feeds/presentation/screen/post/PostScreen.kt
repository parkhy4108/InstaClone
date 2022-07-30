package com.devyoung.feeds.presentation.screen.post

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.composable.CircularIndicatorProgressBar
import com.devyoung.base.R.string as AppText
import com.devyoung.feeds.presentation.composable.PostTopBar
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun PostScreen(
    popUpScreen: () -> Unit,
    viewModel: PostViewModel = hiltViewModel()
) {

    val postState by viewModel.postState


    val selectImageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            viewModel.onImageChange(it)
        }

    LaunchedEffect(true) {
        selectImageLauncher.launch("image/*")
    }
    Box(modifier = Modifier.fillMaxSize()){
        if(!postState.loading){
            Column(modifier = Modifier.fillMaxSize()) {

                PostTopBar(
                    modifier = Modifier,
                    text = stringResource(id = AppText.postTitle),
                    backgroundColor = Color.White,
                    elevation = 0.dp,
                    onGalleryButtonClick = { selectImageLauncher.launch("image/*") },
                    onAddButtonClick = { viewModel.onSavePostClick(popUpScreen) }
                )

                Box(modifier = Modifier.fillMaxHeight(0.6f)){
                    GlideImage(imageModel = postState.imageUrl, modifier = Modifier.padding(8.dp))
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(text = "Comment", modifier = Modifier.padding(5.dp))

                Spacer(modifier = Modifier.height(4.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(4.dp),
                    value = postState.comments,
                    onValueChange = viewModel::onCommentsChange,
                    placeholder = { Text(text = stringResource(id = AppText.postPlaceholder))}
                )
            }
        }
        CircularIndicatorProgressBar(isDisplayed = postState.loading)
    }


}