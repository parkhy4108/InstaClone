package com.devyoung.profile.presentation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.composable.CircularIndicatorProgressBar
import com.devyoung.profile.data.data_source.User
import com.devyoung.profile.presentation.composable.*

private const val CELL_COUNT = 3
private val span: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(CELL_COUNT) }

@Composable
fun ProfileScreen(
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
        profileState.user?.let { ProfileTopBar(text = it.userNickName, backgroundColor = Color.White, elevation = 0.dp, onClick = {viewModel.userLogOut()}) }
        Spacer(modifier = Modifier.height(3.dp))
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(CELL_COUNT)
            ){
                item(span = span) {
                    profileState.user?.let { UserSection(user = it) }
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
    posts: ArrayList<String>,
    imageHeight: Int,
){
    items(posts){ post ->
        val img = imgLoad(imgUrl = post.toUri()).value
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
            Text(text = "프로필", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = "사진과 동영상을 공유하면 프로필에 표시됩니다.", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = "첫 사진 동영상을 공유해보세요", fontSize = 14.sp)
        }
    }
}


