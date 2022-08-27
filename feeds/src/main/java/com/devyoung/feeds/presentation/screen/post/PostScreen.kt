package com.devyoung.feeds.presentation.screen.post

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.composable.addFocusCleaner
import com.devyoung.base.R.string as AppText
import com.devyoung.base.R.drawable as AppImg
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import com.devyoung.base.composable.FitImgLoad
import com.devyoung.base.composable.ShowDialog

@Composable
fun PostScreen(
    popUpScreen: () -> Unit,
    viewModel: PostViewModel = hiltViewModel()
) {
    val postState by viewModel.postState

    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current

    val selectImageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            viewModel.onImageChanged(it, popUpScreen)
        }


    LaunchedEffect(true) {
        selectImageLauncher.launch("image/*")
    }

    if (postState.imageUrl != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .addFocusCleaner(focusManager)
        ) {
            PostTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White),
                text = stringResource(id = AppText.postTitle),
                circleLoading = postState.circleLoading,
                onGalleryButtonClick = { selectImageLauncher.launch("image/*") },
                onAddButtonClick = { viewModel.onSavePostClick() },
                onBackButtonClick = { viewModel.onBackButtonClicked(popUpScreen) }
            )
            Divider(color = Color.LightGray)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.125f)
            ) {
                FitImgLoad(
                    imgUrl = postState.imageUrl,
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(RectangleShape)
                        .fillMaxWidth(0.2f)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    OutlinedTextField(
                        value = postState.comments,
                        onValueChange = viewModel::onCommentsChanged,
                        placeholder = { Text(text = stringResource(id = AppText.postPlaceholder)) },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(4.dp)
                            .focusRequester(focusRequester)
                            .pointerInput(Unit) {
                                detectTapGestures(onTap = {
                                    focusManager.clearFocus()
                                })
                            }
                    )
                }
            }
            Divider(color = Color.LightGray)

        }
    }
    if(postState.openDialog){
        ShowDialog(
            text = AppText.postAdd,
            onDismissRequest = { viewModel.onDialogCancel() },
            confirmButton = { viewModel.onDialogConfirmClick(popUpScreen) },
            dismissButton = { viewModel.onDialogCancel() }
        )
    }
}

@Composable
fun PostTopBar(
    modifier: Modifier,
    text: String,
    circleLoading: Boolean,
    onGalleryButtonClick: () -> Unit,
    onAddButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.Start,
        ) {
            IconButton(onClick = { onBackButtonClick() }) {
                Icon(
                    modifier = Modifier
                        .size(25.dp),
                    painter = painterResource(id = AppImg.ic_back),
                    contentDescription = null
                )
            }
        }
        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier.padding(5.dp),
                text = text,
                fontSize = 15.sp,
                color = Color.Black
            )
        }
        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onGalleryButtonClick() }) {
                Icon(
                    modifier = Modifier
                        .then(Modifier.size(25.dp)),
                    painter = painterResource(id = AppImg.ic_add_photo),
                    contentDescription = null
                )
            }
            Box {
                IconButton(onClick = { onAddButtonClick() }) {
                    if (circleLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(20.dp),
                            color = MaterialTheme.colors.primary,
                            strokeWidth = 2.dp
                        )
                    }
                    else {
                        Icon(
                            modifier = Modifier
                                .size(25.dp),
                            painter = painterResource(id = AppImg.ic_okay),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}
