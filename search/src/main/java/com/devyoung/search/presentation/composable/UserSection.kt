package com.devyoung.search.presentation.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.devyoung.base.composable.CircularIndicatorProgressBar
import com.devyoung.base.composable.ProfileImg
import com.devyoung.search.data.User
import com.devyoung.search.presentation.user.UserState


@Composable
fun UserSection(
    user : User,
    onFollowButtonClick: ()->Unit,
    state : UserState
){
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
            ProfileImg(imgUrl = user.userImage.toUri())
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
            modifier = Modifier.padding(15.dp, 2.dp),
            text = user.userNickName)

        if(state.searchMyself){
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 2.dp),
                onClick = { }
            ) {
                Text(text = "프로필 편집")
            }
        }
        else {
            if(state.hasRequest){
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 2.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { onFollowButtonClick() }
                ) {
                    Box(modifier = Modifier){
                        Text(text = "요청됨")
                        CircularIndicatorProgressBar(isDisplayed = state.buttonLoading)
                    }
                }
            }else{
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp, 2.dp),
                    onClick = { onFollowButtonClick() }
                ) {
                    Text(text = "팔로우")
                }
            }
        }



    }

}

