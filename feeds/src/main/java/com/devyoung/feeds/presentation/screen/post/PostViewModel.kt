package com.devyoung.feeds.presentation.screen.post

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.devyoung.base.InstaViewModel
import com.devyoung.feeds.data.model.Post
import com.devyoung.feeds.domain.usecase.GetUserId
import com.devyoung.feeds.domain.usecase.SavePost
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val savePost: SavePost,
    private val getUserId: GetUserId
) : InstaViewModel() {

    private val userId = getUserId.invoke()

    var postState = mutableStateOf(PostState())
        private set

    private val imageUri get() = postState.value.imageUrl

    private val comments get() = postState.value.comments


    fun onImageChange(newImageUri: String){
        postState.value = postState.value.copy(imageUrl = newImageUri)
    }

    fun onCommentsChange(newComments: String) {
        postState.value = postState.value.copy(comments = newComments)
    }

    fun onSavePostClick(
        popUpScreen : () -> Unit
    ) {
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val time = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val currentTime : Long = System.currentTimeMillis()
        viewModelScope.launch {
            savePost(
                post = Post(
                    postId = UUID.randomUUID().toString(),
                    postImg = imageUri,
                    userId = userId.toString(),
                    date = date.format(currentTime),
                    time = time.format(currentTime),
                    comments = comments,
                    like = 0
                )
            ) { exception ->
                if (exception == null) popUpScreen()
                else onError(exception)
            }
        }
    }



}
