package com.devyoung.feeds.presentation.screen.feed

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.devyoung.base.InstaViewModel
import com.devyoung.base.Screen
import com.devyoung.feeds.data.model.Post
import com.devyoung.feeds.data.model.Story
import com.devyoung.feeds.data.model.User
import com.devyoung.feeds.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    getUserEmail: GetUserEmail,
    private val getMyInfo: GetMyInfo,
    private val getFeed: GetFeed,
    private val getStories: GetStories
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

    fun onCommentClick(){
        Log.d(TAG, "onCommentClick: 클릭처리")
    }

    fun getMyInfo(){
        val list :ArrayList<User> = arrayListOf()
        viewModelScope.launch(Dispatchers.Default) {
            if (email != null) {
                getMyInfo(email,::onError){
                    for (i in 0..10) list.add(it)
                    feedState.value = feedState.value.copy(myInfo = it)
                    feedState.value = feedState.value.copy(userList = list)
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
        // 상대방 화면으로 이동
    }


    fun onStoryClicked(openScreen: (String) -> Unit , story: Story) {
        val userNickName = story.userNickName
        val userImage = URLEncoder.encode(story.userImage, StandardCharsets.UTF_8.toString())
        val userStoryImg = URLEncoder.encode(story.userStoryImg, StandardCharsets.UTF_8.toString())
        openScreen(Screen.UserStory.passStory(userNickName = userNickName, userImage = userImage, storyImage = userStoryImg))

    }

    fun onStoryAddClicked(openScreen: (String) -> Unit){
        openScreen(Screen.StoryAdd.route)
    }

    fun dropDownOpen() {
        feedState.value = feedState.value.copy(isDropExpanded = true)
    }

    fun dropDownCancel(){
        feedState.value = feedState.value.copy(isDropExpanded = false)
    }

    fun getStories(){
        viewModelScope.launch(Dispatchers.Default) {
            if (email != null) {
                getStories(email, ::onError){ list ->
                    feedState.value = feedState.value.copy(storyList = list)
                }
            }
        }
    }



    fun getFeed(){
        viewModelScope.launch(Dispatchers.Default) {
            if (email != null) {
                getFeed(email, ::onError){
                    feedState.value = feedState.value.copy(feedList = it)
                }
            }
        }
    }
}
