package com.devyoung.feeds.presentation.screen.feed

import androidx.compose.runtime.mutableStateOf
import com.devyoung.base.InstaViewModel
import com.devyoung.base.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(

) : InstaViewModel(){

    var feedState = mutableStateOf(FeedState())
        private set





    fun onPostAddClick(
        openScreen: (String) -> Unit
    ) {
        openScreen(Screen.Post.route)
    }

    fun onHeartClick(openScreen: (String) -> Unit) {
        openScreen(Screen.FollowerRequest.route)
    }


}