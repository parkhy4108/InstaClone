package com.devyoung.feeds.presentation.screen.followerRequest

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.composable.CircularIndicatorProgressBar
import com.devyoung.base.composable.ProfileImg
import com.devyoung.feeds.R

@Composable
fun FollowerRequestScreen(
    popUpScreen: () -> Unit,
    viewModel: RequestViewModel = hiltViewModel()
) {
    val requestState by viewModel.requestState

//    val mapState by remember {
//        viewModel.mapState
//    }
//    Log.d(TAG, "screen mapState: $mapState ")

    LaunchedEffect(true) {
        viewModel.loadFollowerRequest()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { viewModel.onBack(popUpScreen) }
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = null)
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 0.dp),
                text = "팔로우 요청"
            )
        }
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(requestState.followerList) { follower ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            if (requestState.view) {
                                Row(
                                    modifier = Modifier.fillParentMaxWidth(0.5f),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    ProfileImg(imgUrl = follower.userImage.toUri())
                                    Text(
                                        text = follower.userNickName,
                                        modifier = Modifier.padding(10.dp, 0.dp)
                                    )
                                }
                                Log.d(
                                    TAG,
                                    "FollowerRequestScreen: ${requestState.requestMap[follower.userEmail]}"
                                )
                                if (requestState.requestMap[follower.userEmail]==0){
//                                if (mapState==0) {
                                    Row(
                                        modifier = Modifier.fillParentMaxWidth(0.5f),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        Button(
                                            modifier = Modifier.size(60.dp, 30.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                backgroundColor = Color(
                                                    0xFF1E88E5
                                                )
                                            ),
                                            onClick = { viewModel.onAccept(follower.userEmail) }
                                        ) {
                                            Text(text = "확인", fontSize = 10.sp)
                                        }
                                        Button(
                                            modifier = Modifier.size(60.dp, 30.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                backgroundColor = Color(
                                                    0xFF9DA3A8
                                                )
                                            ),
                                            onClick = { viewModel.onDelete(follower.userEmail) }
                                        ) {
                                            Text(text = "삭제", fontSize = 10.sp)
                                        }
                                    }
                                } else if (requestState.requestMap[follower.userEmail] == 1) {
                                    Row(
                                        modifier = Modifier.fillParentMaxWidth(0.5f),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        Button(
                                            modifier = Modifier.size(80.dp, 30.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                backgroundColor = Color(
                                                    0xFF1E88E5
                                                )
                                            ),
                                            onClick = {
                                                viewModel.onFollowButtonClicked(
                                                    follower.userEmail
                                                )
                                            }
                                        ) {
                                            Text(text = "맞팔로우 하기", fontSize = 10.sp)
                                        }
                                    }
                                } else {
                                    Row(
                                        modifier = Modifier.fillParentMaxWidth(0.5f),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        Button(
                                            modifier = Modifier.size(70.dp, 30.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                backgroundColor = Color(
                                                    0xFF1E88E5
                                                )
                                            ),
                                            onClick = {
                                                viewModel.onFollowButtonClicked(
                                                    follower.userEmail
                                                )
                                            }
                                        ) {
                                            Text(text = "요청됨", fontSize = 10.sp)
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
            CircularIndicatorProgressBar(isDisplayed = requestState.loading)
        }
    }
}


//ProfileImg(imgUrl = follower.userImage.toUri())
//Text(text = follower.userNickName)
//Button(
//colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1E88E5)),
//onClick = { viewModel.onAccept() }
//) {
//    Text(text = "확인", fontSize = 10.sp)
//}
//Button(
//colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF9DA3A8)),
//onClick = { viewModel.onDelete() }
//) {
//    Text(text = "삭제", fontSize = 10.sp)
//}


//Row(
//modifier = Modifier.fillMaxWidth(),
//verticalAlignment = Alignment.CenterVertically,
//) {
//    Button(
//        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1E88E5)),
//        onClick = { viewModel.onAccept() }
//    ) {
//        Text(text = "확인", fontSize = 10.sp)
//    }
//    Button(
//        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF9DA3A8)),
//        onClick = { viewModel.onDelete() }
//    ) {
//        Text(text = "삭제", fontSize = 10.sp)
//    }
//}


//Row(
//modifier = Modifier.fillParentMaxWidth(0.5f),
//verticalAlignment = Alignment.CenterVertically,
//horizontalArrangement = Arrangement.End
//) {
//    Button(
//        modifier = Modifier.size(60.dp, 30.dp),
//        colors = ButtonDefaults.buttonColors(
//            backgroundColor = Color(
//                0xFF1E88E5
//            )
//        ),
//        onClick = { viewModel.onAccept(follower.userEmail) }
//    ) {
//        Text(text = "확인", fontSize = 10.sp)
//    }
//    Button(
//        modifier = Modifier.size(60.dp, 30.dp),
//        colors = ButtonDefaults.buttonColors(
//            backgroundColor = Color(
//                0xFF9DA3A8
//            )
//        ),
//        onClick = { viewModel.onDelete() }
//    ) {
//        Text(text = "삭제", fontSize = 10.sp)
//    }
//}


//Row(
//modifier = Modifier.fillParentMaxWidth(0.5f),
//verticalAlignment = Alignment.CenterVertically,
//horizontalArrangement = Arrangement.End
//){
//    Button(
//        modifier = Modifier.size(60.dp, 30.dp),
//        colors = ButtonDefaults.buttonColors(
//            backgroundColor = Color(
//                0xFF1E88E5
//            )
//        ),
//        onClick = { viewModel.onFollowButtonClicked(follower.userEmail) }
//    ) {
//        Text(text = "요청됨")
//    }
//}


//Row(
//modifier = Modifier.fillParentMaxWidth(0.5f),
//verticalAlignment = Alignment.CenterVertically,
//horizontalArrangement = Arrangement.End
//){
//    Button(
//        modifier = Modifier.size(80.dp, 30.dp),
//        colors = ButtonDefaults.buttonColors(
//            backgroundColor = Color(
//                0xFF1E88E5
//            )
//        ),
//        onClick = { viewModel.onFollowButtonClicked(follower.userEmail) }
//    ) {
//        Text(text = "맞팔로우 하기", fontSize = 10.sp)
//    }
//}


//Row(
//modifier = Modifier.fillParentMaxWidth(0.5f),
//verticalAlignment = Alignment.CenterVertically,
//horizontalArrangement = Arrangement.End
//){
//    if(requestState.hasRequest){
//        Button(
//            modifier = Modifier.size(60.dp, 30.dp),
//            colors = ButtonDefaults.buttonColors(
//                backgroundColor = Color(
//                    0xFF1E88E5
//                )
//            ),
//            onClick = { viewModel.onFollowButtonClicked(follower.userEmail) }
//        ) {
//            Text(text = "요청됨")
//        }
//    }
//    else {
//        Button(
//            modifier = Modifier.size(80.dp, 30.dp),
//            colors = ButtonDefaults.buttonColors(
//                backgroundColor = Color(
//                    0xFF1E88E5
//                )
//            ),
//            onClick = { viewModel.onFollowButtonClicked(follower.userEmail) }
//        ) {
//            Text(text = "맞팔로우 하기", fontSize = 10.sp)
//        }
//    }
//}