package com.devyoung.feeds.presentation.screen.feed

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.devyoung.base.BottomBarScreen
import com.devyoung.base.InstaViewModel
import com.devyoung.base.Screen
import com.devyoung.feeds.data.model.Post
import com.devyoung.feeds.data.model.User
import com.devyoung.feeds.domain.usecase.GetFeed
import com.devyoung.feeds.domain.usecase.GetMyInfo
import com.devyoung.feeds.domain.usecase.GetUserEmail
import com.google.firebase.firestore.model.FieldIndex
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getMyInfo: GetMyInfo,
    private val getFeed: GetFeed,
    private val getUserEmail: GetUserEmail
) : InstaViewModel(){

    var feedState = mutableStateOf(FeedState())
        private set

    var isSelectedState = mutableStateMapOf<Int, Boolean>()
        private set

    var isLikedState = mutableStateMapOf<Post, Boolean>()


    val email = getUserEmail()

    fun onFollowButtonClick(index: Int ){
        isSelectedState[index]  = when(isSelectedState[index]){
            true -> false
            else -> true
        }
    }

    fun onLikeButtonClick(post: Post){
        isLikedState[post] = when(isLikedState[post]){
            true -> false
            else -> true
        }
    }

    fun onCommentClick(post: Post){
        Log.d(TAG, "onCommentClick: 클릭처리")
    }

    fun getMyInfo(){
        val list : ArrayList<User> = arrayListOf()
        viewModelScope.launch(exceptionHandler) {
            if (email != null) {
                getMyInfo(email,::onError){
                    // 스토리에 여러명 불러오는걸 보기 위해서 넣어둔 상태
                    for (i in 0..10){
                        list.add(it)
                    }
                    feedState.value = feedState.value.copy(userList = list)
                    feedState.value = feedState.value.copy(myInfo = it)

                }
            }
        }
    }

    fun onPostAddClick(
        openScreen: (String) -> Unit
    ) {
        openScreen(Screen.Post.route)
    }

    fun onHeartClick(openScreen: (String) -> Unit) {
        openScreen(Screen.FollowerRequest.route)
    }


    fun onCardClicked(){

    }

    fun onStoryClicked(){

    }

    fun getStories(){
        viewModelScope.launch(exceptionHandler) {

        }
    }

    fun getFeed(){
        viewModelScope.launch(exceptionHandler) {
            if (email != null) {
                getFeed(email, ::onError){
                    feedState.value = feedState.value.copy(feedList = it)
                }
            }
        }
    }



}