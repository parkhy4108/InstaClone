package com.devyoung.search.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.composable.CircularIndicatorProgressBar
import com.devyoung.search.R
import com.devyoung.base.composable.loadPicture

@Composable
fun SearchDetailScreen(
    openScreen: (String) -> Unit,
    popUpScreen: () -> Unit,
    viewModel: SearchDetailViewModel = hiltViewModel()
){

    val searhDetailState by viewModel.searchState

    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit){
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            IconButton(
                onClick = { viewModel.onBack(popUpScreen) }
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = null)
            }

            OutlinedTextField(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .fillMaxWidth()
                    .height(48.dp)
                    .focusRequester(focusRequester)
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            focusManager.clearFocus()
                        })
                    }
                ,
                value = searhDetailState.searchText,
                onValueChange = viewModel::onSearchTextChanged,
                trailingIcon = {
                    if (searhDetailState.searchText.isNotBlank() ){
                        IconButton(
                            onClick = { viewModel.onSearchTextCleared() }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = null
                            )
                        }
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.LightGray,
                    focusedIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.LightGray
                ),
                placeholder = {
                    Text(
                        text = "검색",
                        fontSize = 13.sp
                    )
                              },
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 13.sp
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        viewModel.onSearch()
                    }
                )
            )

        }

        Spacer(modifier = Modifier
            .height(1.dp)
            .background(Color.LightGray)
            .fillMaxWidth())


        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (searhDetailState.view){
                val img = loadPicture(uri = searhDetailState.profileImg, defaultImage = R.drawable.ic_empty_user_img).value
                if (img != null) {
                    LazyColumn(
                        modifier=Modifier.fillMaxWidth(),
                    ) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .clickable(
                                        onClick = {
                                            viewModel.onUserClick(
                                                openScreen,
                                                searhDetailState.userEmail
                                            )
                                        }
                                    )
                            ){
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,

                                    ) {

                                    Image(
                                        bitmap = img.asImageBitmap(),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(75.dp)
                                            .clip(CircleShape)
                                            .border(2.dp, Color.Blue, CircleShape)

                                    )
                                    Text(
                                        text = searhDetailState.nickName,
                                        modifier = Modifier.padding(10.dp)
                                    )
                                }
                            }

                        }
                    }
                }
            }
            CircularIndicatorProgressBar(isDisplayed = searhDetailState.loading)
        }



    }
}

fun Modifier.addFocusCleaner(focusManager: FocusManager, doOnClear:() -> Unit = {}): Modifier {
    return this.pointerInput(Unit){
        detectTapGestures(onTap = {
            doOnClear()
            focusManager.clearFocus()
        })
    }
}

