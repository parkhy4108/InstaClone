package com.devyoung.feeds.presentation.composable


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.devyoung.feeds.R
import com.devyoung.feeds.data.model.User

//@Composable
//fun FeedUserView(user: User){
//    val shouldLikeIconChange = remember {
//        mutableStateOf(false)
//    }
//    val likeIconResource : (Boolean) -> Int = {
//        if (it) R.drawable.ic_like
//        else R.drawable.ic_nolike
//    }
//    Column(
//        modifier = Modifier
//    ) {
//        Row(
//            modifier = Modifier
//                .padding(10.dp, 8.dp, 0.dp, 8.dp)
//                .fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Img(
//                imgUrl = user.profileImage,
//                modifier = Modifier
//                    .size(30.dp)
//                    .clip(CircleShape)
//            )
//            Column(
//                modifier = Modifier,
//                verticalArrangement = Arrangement.Center
//            ){
//                Text(text = user.name)
//            }
//        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center
//        ){
//            Box(modifier = Modifier
//                .fillMaxHeight(0.8f)
//            ){
//                Img(
//                    imgUrl = user.profileImage,
//                    modifier = Modifier
//                        .clip(RectangleShape)
//                        .fillMaxSize()
//                )
//            }
//
//        }
//
//        Row(modifier= Modifier
//            .fillMaxWidth()
//        ) {
//            Box(modifier = Modifier
//                .fillMaxHeight(0.3f)
//            ){
//                IconButton(
//                    onClick = {
//                        shouldLikeIconChange.value = !shouldLikeIconChange.value
//                    }
//                ) {
//                    Icon(
//                        painter = painterResource(
//                            id = likeIconResource(shouldLikeIconChange.value)
//                        ),
//                        contentDescription = null)
//                }
//            }
//
//        }
//
//        Row(modifier = Modifier
//            .fillMaxWidth()
//        ) {
//            Text(text = user.description)
//        }
//
//    }
//}


