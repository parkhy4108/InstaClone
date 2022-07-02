package com.devyoung.feeds.presentation.composable

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.devyoung.feeds.data.User

@Composable
fun StoryList(user: List<User>){
    LazyRow(modifier = Modifier){
        items(user){
            StoryUserView(user = it)
        }
    }
}