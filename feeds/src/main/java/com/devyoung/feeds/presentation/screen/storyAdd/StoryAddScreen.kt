package com.devyoung.feeds.presentation.screen.storyAdd

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.R.string as AppText
import com.devyoung.base.composable.FitImgLoad
import com.devyoung.base.composable.ShowDialog

@Composable
fun StoryAddScreen(
    popUpScreen: ()->Unit,
    viewModel: StoryAddViewModel = hiltViewModel()
) {

    val state by viewModel.state

    val selectImageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            viewModel.onImageChanged(it, popUpScreen)
        }

    LaunchedEffect(true) {
        selectImageLauncher.launch("image/*")
    }


    val okayButtonColor = if(state.imageUrl!=null) Color.Blue else Color.LightGray

    if(state.imageUrl!=null){
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopStart
            ){
                FitImgLoad(
                    imgUrl = state.imageUrl,
                    modifier = Modifier.fillMaxSize()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    TextButton(
                        modifier = Modifier,
                        onClick = { viewModel.onBackButtonClicked(popUpScreen) }) {
                        Text(
                            text = stringResource(id = AppText.dismiss),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue
                        )
                    }
                    Box {
                        TextButton(
                            onClick = {
                                viewModel.onSaveButtonClicked()
                            },
                            enabled = !state.circleLoading
                        ) {
                            if (state.circleLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(20.dp),
                                    color = MaterialTheme.colors.primary,
                                    strokeWidth = 2.dp
                                )
                            }
                            else{
                                Text(
                                    text = stringResource(id = AppText.confirm),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = okayButtonColor
                                )
                            }
                        }
                    }
                }
            }
        }

    }

    if(state.openDialog){
        ShowDialog(
            text = AppText.storyAdd,
            onDismissRequest = { viewModel.onDialogCancel() },
            confirmButton = { viewModel.onDialogConfirmClick(popUpScreen) },
            dismissButton = { viewModel.onDialogCancel() }
        )
    }
}

