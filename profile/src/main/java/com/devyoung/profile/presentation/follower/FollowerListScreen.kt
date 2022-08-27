package com.devyoung.profile.presentation.follower

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.composable.ImgLoad
import com.devyoung.base.composable.addFocusCleaner
import com.devyoung.base.R.string as AppText

@Composable
fun FollowerListScreen(
    popUpScreen: () -> Unit,
    viewModel: FollowerListViewModel = hiltViewModel()
) {
    val editState by viewModel.editState
    val selectImageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            viewModel.onImageChanged(it)
        }
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(true) {
        viewModel.getUserInfo()
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .addFocusCleaner(focusManager),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                modifier = Modifier,
                onClick = { viewModel.onBackButtonClicked(popUpScreen) }) {
                Text(
                    text = stringResource(id = AppText.dismiss)
                )
            }
            Text(text = stringResource(id = AppText.editProfile))
            Box(modifier = Modifier){
                TextButton(
                    onClick = {
                        focusManager.clearFocus()
                        viewModel.onDialogOpen()
                    },
                    enabled = !editState.isDisplayed
                ) {
                    if (editState.isDisplayed) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(20.dp),
                            color = MaterialTheme.colors.primary,
                            strokeWidth = 2.dp
                        )
                    }
                    else{
                        Text(
                            text = stringResource(id = AppText.confirm)
                        )
                    }
                }
            }

        }

        Divider(color = Color.LightGray)

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        focusManager.clearFocus()
                        selectImageLauncher.launch("image/*")
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                ImgEdit(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color(0xFF1976D2), CircleShape),
                    img = editState.imageUrl
                )
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = stringResource(id = AppText.editPicture), color = Color(0xFF1976D2)
                )
            }
            Divider(color = Color.LightGray)


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .padding(10.dp, 0.dp, 0.dp, 0.dp),
                        text = stringResource(id = AppText.name)
                    )
                    NickNameTextField(
                        check = editState.check,
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(50.dp)
                            .focusRequester(focusRequester)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onTap = {
                                        focusManager.clearFocus()
                                    },
                                    onPress = {
                                        focusManager.clearFocus()
                                    })
                            },
                        nickName = editState.nickName,
                        onChanged = viewModel::onNickNameChanged,
                    )
                }
                Divider(color = Color.LightGray)
            }
        }
    }


    if (editState.openDialog)
        AlertDialog(
            onDismissRequest = { viewModel.onDialogCancel() },
            text = {
                Text(text = stringResource(id = AppText.editDialog))
            },
            confirmButton = {
                Button(onClick = { viewModel.onConfirmClicked() }) {
                    Text(text = stringResource(id = AppText.confirm))
                }
            },
            dismissButton = {
                Button(onClick = { viewModel.onDialogCancel() }) {
                    Text(text = stringResource(id = AppText.dismiss))
                }
            },
        )
}


@Composable
fun ImgEdit(
    modifier: Modifier,
    img: String?
) {
    ImgLoad(
        imgUrl = img?.toUri(),
        modifier = modifier
    )
}

@Composable
fun NickNameTextField(
    check: Boolean,
    modifier: Modifier,
    nickName: String,
    onChanged: (String) -> Unit
) {
    val buttonColor = if (check) Color.Red else Color.White
    OutlinedTextField(
        modifier = modifier,
        value = nickName,
        onValueChange = onChanged,
        placeholder = { Text(text = nickName) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = buttonColor,
            focusedBorderColor = buttonColor
        )
    )
}

