package com.devyoung.profile.presentation.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devyoung.profile.data.data_source.User

@Composable
fun UserSection(user : User){
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileImg(imgUrl = user.userImage)
            Column(
                modifier = Modifier
                    .padding(5.dp, 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${user.postNum}")
                Text(text = "게시물")
            }
            Column(
                modifier = Modifier
                    .padding(5.dp, 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${user.follower}")
                Text(text = "팔로워")
            }
            Column(
                modifier = Modifier
                    .padding(5.dp, 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${user.following}")
                Text(text = "팔로잉")
            }
        }
        Text(
            modifier = Modifier.padding(14.dp, 2.dp),
            text = user.userNickName)
    }

}

