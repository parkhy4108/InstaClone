package com.devyoung.profile.presentation.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devyoung.profile.data.data_source.Post
import com.devyoung.profile.data.data_source.User
import com.skydoves.landscapist.glide.GlideImage

//@Composable
//fun GridSection(
//    user: User,
//    posts:List<Post>,
//    width: Int
//) {
//
//
////        if (user.postNum==0){
////            item {
////                Card(modifier = Modifier) {
////                    Text(text = "프로필")
////                    Text(text = "사진과 동영상을 공유하면 프로필에 표시됩니다.")
////                    Text(text = "첫 사진 동영상을 공유해보세요")
////                }
////            }
////        }
////        else {
////            items(user.postNum) {
////                Card(
////                    modifier = Modifier
////                        .height(imageHeight.dp)
////                ) {
////
////                }
////            }
////        }
//
//    }
//}
//
//sealed class HasPost {
//    object ABSENCE : HasPost()
//    object PRESENCE : HasPost()
//}