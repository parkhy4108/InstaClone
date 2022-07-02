package com.devyoung.feeds.presentation.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devyoung.feeds.data.User

@Composable
fun StoryUserView(user: User){
    Column(
        modifier = Modifier
            .size(80.dp,100.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileImg(
            imgUrl = user.profileImage,
            modifier = Modifier
                .clip(CircleShape)
                .size(55.dp)
        )
        Text(
            modifier =Modifier
                .padding(0.dp,5.dp,0.dp,0.dp),
            text = user.name,
            fontSize = 10.sp,
            textAlign = TextAlign.Center
        )

    }
}