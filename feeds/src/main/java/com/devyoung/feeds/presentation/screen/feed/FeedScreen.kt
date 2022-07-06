package com.devyoung.feeds.presentation.screen.feed

import android.Manifest
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.devyoung.feeds.data.model.DummyDataProvider
import com.devyoung.feeds.presentation.composable.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FeedScreen(
    openScreen: (String) -> Unit,
    viewModel: FeedViewModel = hiltViewModel()
) {
    val dumData = DummyDataProvider.userLists

    val feedState by viewModel.feedState

    Column(modifier = Modifier.fillMaxSize()) {
        FeedTopBar(
            text = "InstaClone",
            backgroundColor = Color.White,
            elevation = 0.dp,
            onAddButtonClick = { viewModel.onPostAddClick(openScreen) }
        )
        LazyColumn{
            item {
                StoryList(user = dumData)
            }
            items(dumData){
                Column(modifier = Modifier
                    .fillParentMaxHeight()) {
                    FeedUserView(user = it)
                }
            }
        }
    }
}