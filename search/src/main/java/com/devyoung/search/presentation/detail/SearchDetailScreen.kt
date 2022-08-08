package com.devyoung.search.presentation.detail

import android.net.Uri
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.composable.CircularIndicatorProgressBar
import com.devyoung.base.composable.ImgLoad
import com.devyoung.base.R.drawable as AppImg

@Composable
fun SearchDetailScreen(
    openScreen: (String) -> Unit,
    popUpScreen: () -> Unit,
    viewModel: SearchDetailViewModel = hiltViewModel()
) {

    val searchDetailState by viewModel.searchState

    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
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
            BackButton(onClick = { viewModel.onBack(popUpScreen) })
            SearchTextField(
                text = searchDetailState.searchText,
                onValueChange = viewModel::onSearchTextChanged,
                onTextCleared = viewModel::onSearchTextCleared,
                onSearch = viewModel::onSearch,
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
            )
        }
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .background(Color.LightGray)
                .fillMaxWidth()
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (searchDetailState.view) {
                UserCard(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Blue, CircleShape),
                    img = searchDetailState.profileImg,
                    nickName = searchDetailState.nickName,
                    onClick = { viewModel.onUserClick(openScreen, searchDetailState.userEmail) }
                )
            }
            CircularIndicatorProgressBar(isDisplayed = searchDetailState.loading)

        }
    }
}




fun Modifier.addFocusCleaner(focusManager: FocusManager, doOnClear: () -> Unit = {}): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            doOnClear()
            focusManager.clearFocus()
        })
    }
}

@Composable
fun BackButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() }
    ) {
        Icon(painter = painterResource(id = AppImg.ic_back), contentDescription = null)
    }
}


@Composable
fun SearchTextField(
    text: String,
    onValueChange: (String) -> Unit,
    onTextCleared: () -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier
) {
    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        modifier = modifier,
        trailingIcon = {
            if (text.isNotBlank()) {
                IconButton(
                    onClick = { onTextCleared() }
                ) {
                    Icon(
                        painter = painterResource(id = AppImg.ic_close),
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
            onDone = { onSearch() }
        )
    )
}

@Composable
fun UserCard(
    modifier: Modifier,
    img: String,
    nickName: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color.White),
                onClick = { onClick() }
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ImgLoad(
                imgUrl = img.toUri(),
                modifier = modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Blue, CircleShape)
            )
            Text(text = nickName, modifier = Modifier.padding(20.dp,10.dp))
        }
    }
}