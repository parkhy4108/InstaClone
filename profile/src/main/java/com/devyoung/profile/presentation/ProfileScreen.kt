package com.devyoung.profile.presentation

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.bumptech.glide.Glide
import com.devyoung.profile.R
import com.devyoung.profile.data.data_source.Post
import com.devyoung.profile.presentation.composable.*
import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProfileScreen(
    restartApp: (String) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit){
        viewModel.getUserInfo()
    }

    val user by viewModel.user
    val profileState by viewModel.profileState

    val imageWidth = LocalConfiguration.current.screenWidthDp
    val imageHeight = imageWidth / 3

    val postStorageReference = profileState.posts

//    val postlist = remember { viewModel.getAllPosts() }


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ProfileTopBar(text = user.userNickName, backgroundColor = Color.White, elevation = 0.dp, onClick = {viewModel.userLogOut(restartApp)})
        Spacer(modifier = Modifier.height(3.dp))


        LazyVerticalGrid(columns = GridCells.Fixed(3)){
            item(span = { GridItemSpan(3) }) {
                UserSection(user = user)
            }
            when (postlist.isEmpty()) {
                true -> {
                    item(span = { GridItemSpan(3) }) {
                        Column(
                            modifier = Modifier
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
                false -> {
                    items(postlist) { post ->
                        Card(
                            modifier = Modifier
                                .height(imageHeight.dp)
                        ) {
                            Log.d(TAG, "ProfileScreen: $post")
//                            ImgLoad(imgUrl = post)
                            val image = loadPicture(uri = post, defaultImage = R.drawable.ic_empty_user_img).value
                            image?.let {
                                Image(
                                    bitmap = image.asImageBitmap(),
                                    contentDescription = null)
                            }
                        }
                        

                    }
                }
            }
        }
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(3)
//        ) {
//            item(span = { GridItemSpan(3) }) {
//                UserSection(user = user)
//            }
//
//        }
    }
}

//
//when (postStorageReference.isEmpty()) {
//    true -> {
//        item(span = { GridItemSpan(3) }) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(300.dp),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(text = "프로필", fontSize = 18.sp, fontWeight = FontWeight.Bold)
//                Spacer(modifier = Modifier.height(3.dp))
//                Text(text = "사진과 동영상을 공유하면 프로필에 표시됩니다.", fontSize = 14.sp)
//                Spacer(modifier = Modifier.height(3.dp))
//                Text(text = "첫 사진 동영상을 공유해보세요", fontSize = 14.sp)
//            }
//        }
//    }
//    false -> {
//        items(postStorageReference){ post ->
//            Card(
//                modifier = Modifier
//                    .height(imageHeight.dp)
//            ) {
//                Log.d(TAG, "ProfileScreen: $post")
//                ImgLoad(imgUrl = post)
//            }
//
//        }
//    }
//}


//if (postStorageReference.isEmpty()){
//    item(span = { GridItemSpan(3) }) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(300.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(text = "프로필", fontSize = 18.sp, fontWeight = FontWeight.Bold)
//            Spacer(modifier = Modifier.height(3.dp))
//            Text(text = "사진과 동영상을 공유하면 프로필에 표시됩니다.", fontSize = 14.sp)
//            Spacer(modifier = Modifier.height(3.dp))
//            Text(text = "첫 사진 동영상을 공유해보세요", fontSize = 14.sp)
//        }
//    }
//}
//else {
//    items(postStorageReference){ post ->
//        Card(
//            modifier = Modifier
//                .height(imageHeight.dp)
//        ) {
//            Log.d(TAG, "ProfileScreen: $post")
//            ImgLoad(imgUrl = post)
//        }
//
//    }
//}

