package com.devyoung.profile.presentation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.composable.TopBar
import com.devyoung.profile.data.User
import com.devyoung.profile.presentation.composable.ProfileImg
import com.devyoung.profile.presentation.composable.RoundImgCard

@Composable
fun ProfileScreen(
    restartApp: (String) -> Unit,
//    openScreen: (String)-> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    val mockUser = User(
        nickName = "HaY",
        userImage = "https://randomuser.me/api/portraits/men/51.jpg",
        postNum = 50,
        follower = 20,
        following = 30,
    )

    val imageWidth = LocalConfiguration.current.screenWidthDp
    val imageHeight = imageWidth/3
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBar(text = mockUser.nickName, backgroundColor = Color.White, elevation = 0.dp)
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            item(span = { GridItemSpan(3)}) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        modifier = Modifier
                            .padding(5.dp, 0.dp,15.dp,5.dp)
                    ) {
                        RoundImgCard(imageUrl = mockUser.userImage, modifier = Modifier)
                        Text(text = mockUser.nickName)
                    }
                    Column(
                        modifier = Modifier
                            .padding(5.dp, 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "${mockUser.postNum}")
                        Text(text = "게시물")
                    }
                    Column(
                        modifier = Modifier
                            .padding(5.dp, 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "${mockUser.follower}")
                        Text(text = "팔로워")
                    }
                    Column(
                        modifier = Modifier
                            .padding(5.dp, 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "${mockUser.following}")
                        Text(text = "팔로잉")
                    }

                }
            }
            items(mockUser.postNum){
                Card(modifier = Modifier
                    .height(imageHeight.dp)) {
                    ProfileImg(imgUrl = mockUser.userImage)
                }
            }
        }






//        viewModel.getEmail()
//        Text(text = uiState.email)
//
////       viewModel.getEmail()?.let { Text(text = it) }
//
//
//        Button(
//            onClick = {
//                viewModel.userLogOut(restartApp)
//                Log.d("TAG", "로그아웃 버튼 클릭")
//            }) {
//            Text(text = "로그아웃")
//        }
    }
}


//LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
//    item {
//        Row(modifier = Modifier.fillMaxWidth()) {
//
//            Column(modifier = Modifier
//                .padding(5.dp, 0.dp,15.dp,5.dp)) {
//                RoundImgCard(imageUrl = mockUser.userImage, modifier = Modifier)
//                Text(text = mockUser.nickName)
//            }
//
//            Row(modifier = Modifier,
//                horizontalArrangement = Arrangement.SpaceBetween) {
//                Box(modifier = Modifier,
//                    contentAlignment = Alignment.Center) {
//                    Text(text = "${mockUser.postNum}")
//                    Text(text = "게시물")
//                }
//                Card(modifier = Modifier) {
//                    Text(text = "${mockUser.follower}")
//                    Text(text = "팔로워")
//                }
//                Card(modifier = Modifier) {
//                    Text(text = "${mockUser.following}")
//                    Text(text = "팔로잉")
//                }
//            }
//
//        }
//    }
//    items(mockUser.postNum){
//        Card(modifier = Modifier) {
//            ProfileImg(imgUrl = mockUser.userImage)
//        }
//    }
//}