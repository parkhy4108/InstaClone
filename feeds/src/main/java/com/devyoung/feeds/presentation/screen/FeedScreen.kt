package com.devyoung.feeds.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devyoung.base.composable.TopBar
import com.devyoung.feeds.data.DummyDataProvider
import com.devyoung.feeds.presentation.FeedViewModel
import com.devyoung.feeds.presentation.composable.*

@Composable
fun FeedScreen(
    openScreen: (String) -> Unit,
    viewModel: FeedViewModel = hiltViewModel()
) {
    val dumData = DummyDataProvider.userLists

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            text = "InstaClone",
            backgroundColor = Color.White,
            elevation = 0.dp
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