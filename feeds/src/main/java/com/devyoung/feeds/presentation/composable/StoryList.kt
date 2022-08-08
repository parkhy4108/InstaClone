package com.devyoung.feeds.presentation.composable

import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.devyoung.base.composable.ImgLoad
import com.devyoung.feeds.data.model.User
import com.devyoung.base.R.string as AppText

@Composable
fun StoryList(users: List<User>, onStoryClicked: () -> Unit){
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(15.dp, 5.dp),
        horizontalArrangement = Arrangement.spacedBy(27.dp)
    ){
        itemsIndexed(items = users) { index, user ->
            when(index){
                0 -> {
                    MyStory(modifier = Modifier, imgUrl = user.userImage.toUri(), onStoryClicked = onStoryClicked )
                }
                else -> {
                    MyPeopleStory(nickName = user.userNickName, imgUrl = user.userImage.toUri(), modifier = Modifier, onStoryClicked = onStoryClicked )
                }
            }
        }
    }
}

@Composable
fun MyStory(modifier: Modifier, imgUrl: Uri, onStoryClicked: ()-> Unit){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImgLoad(imgUrl = imgUrl, modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .border(1.dp, Color(0xFF1976D2), CircleShape)
            .clickable { onStoryClicked() }
        )
        Text(text = stringResource(id = AppText.myStory), fontSize = 10.sp, textAlign = TextAlign.Center)
    }
}

@Composable
fun MyPeopleStory(nickName: String, imgUrl: Uri, modifier : Modifier, onStoryClicked: ()-> Unit){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImgLoad(
            imgUrl = imgUrl,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(1.dp, Color(0xFF1976D2), CircleShape)
                .clickable { onStoryClicked() })
        Text(text = nickName, fontSize = 10.sp, textAlign = TextAlign.Center)
    }
}