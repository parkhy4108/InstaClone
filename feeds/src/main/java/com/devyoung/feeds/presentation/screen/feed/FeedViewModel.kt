package com.devyoung.feeds.presentation.screen.feed

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.devyoung.base.POST_SCREEN
import com.devyoung.base.URL
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
) : ViewModel(){

    var feedState = mutableStateOf(FeedState())
        private set


    fun onPostAddClick(
        openScreen:(String)-> Unit
    ) {
        openScreen(POST_SCREEN)
    }

//    fun onImageChange(newImageUri: String){
//        feedState.value = feedState.value.copy(imageUrl = newImageUri)
//    }



}