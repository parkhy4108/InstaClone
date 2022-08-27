package com.devyoung.profile.presentation.profileHome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.composable.ImgLoad
import com.devyoung.profile.presentation.composable.*
import com.devyoung.base.R.string as AppText
import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import com.devyoung.base.composable.FitImgLoad


private const val CELL_COUNT = 3
private val span: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(CELL_COUNT) }

@Composable
fun ProfileScreen(
    openScreen: (String) -> Unit,
    restartApp: (String) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val profileState by viewModel.profileState

    LaunchedEffect(Unit){
        viewModel.getUserInfo()
        viewModel.getAllPost()
    }

    val imageWidth = LocalConfiguration.current.screenWidthDp
    val imageHeight = imageWidth / 3

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        profileState.user?.let {
            ProfileTopBar(
                text = it.userNickName,
                onClick = {viewModel.userLogOut(restartApp)} ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp, 0.dp)
                    .background(Color.White)
            ) }
        Spacer(modifier = Modifier.height(3.dp))
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(CELL_COUNT)
            ){
                item(span = span) {
                    profileState.user?.let {
                        UserSection(user = it, openScreen = {viewModel.onProfileEditClick(openScreen)})
                    }
                }
                if(profileState.user?.postNum != 0){
                    postItem(posts = profileState.posts, imageHeight = imageHeight)
                }
                else {
                    nothingItemView(span = span, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

fun LazyGridScope.postItem(
    posts: List<Uri>,
    imageHeight: Int,
){
    items(posts){ post ->
        FitImgLoad(imgUrl = post,modifier =Modifier.height(imageHeight.dp))
    }
}


fun LazyGridScope.nothingItemView(
    span: (LazyGridItemSpanScope) -> GridItemSpan,
    modifier: Modifier
){
    item(span = span) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(300.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = AppText.profile), fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = stringResource(id = AppText.profileDescription1), fontSize = 14.sp)
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = stringResource(id = AppText.profileDescription2), fontSize = 14.sp)
        }
    }
}


