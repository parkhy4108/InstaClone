package com.devyoung.feeds.presentation.screen.post

import android.net.Uri
import androidx.compose.runtime.*
import androidx.core.net.toUri
import androidx.lifecycle.viewModelScope
import com.devyoung.base.InstaViewModel
import com.devyoung.base.SnackbarManager
import com.devyoung.feeds.data.model.Post
import com.devyoung.feeds.domain.usecase.GetUserEmail
import com.devyoung.feeds.domain.usecase.SavePost
import com.devyoung.feeds.domain.usecase.UpdatePostNum
import com.devyoung.feeds.domain.usecase.UploadFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val savePost: SavePost,
    private val getUserEmail: GetUserEmail,
    private val userPostNum: UpdatePostNum,
    private val uploadFile: UploadFile
) : InstaViewModel() {

    var postState = mutableStateOf(PostState())
        private set

    private val imageUri get() = postState.value.imageUrl
    private val comments get() = postState.value.comments

    private val email = getUserEmail()

    fun onImageChange(newImageUri: Uri?) {
        postState.value = postState.value.copy(imageUrl = newImageUri)
    }

    fun onCommentsChange(newComments: String) {
        postState.value = postState.value.copy(comments = newComments)
    }

    fun onSavePostClick(
        popUpScreen: () -> Unit
    ) {
        savePost(popUpScreen)
    }

    private fun savePost(popUpScreen: () -> Unit){
        val postId = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val time = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val currentTime: Long = System.currentTimeMillis()

        viewModelScope.launch(exceptionHandler) {
            val userEmail = getUserEmail()
            savePost(
                userEmail.toString(),
                post = Post(
                    postId = postId.format(currentTime).toString(),
                    postImg = imageUri.toString(),
                    userId = userEmail.toString(),
                    date = date.format(currentTime).toString(),
                    time = time.format(currentTime).toString(),
                    comments = comments,
                    like = 0
                )
            ) { error ->
                if (error != null) onError(error)
                else uploadFile(
                    postId = postId.format(currentTime).toString() ,
                    uri = imageUri.toString() ,
                    popUpScreen = popUpScreen
                )
            }
        }
    }

    private fun uploadFile(postId: String, uri: String, popUpScreen: () -> Unit) {
        viewModelScope.launch(exceptionHandler) {
            postState.value = postState.value.copy(loading = true)
            delay(4000)
            uploadFile(email.toString(), postId ,uri.toUri()) { error ->
                if (error == null) updateUserPostNumber(popUpScreen)
                else onError(error)
            }
        }
    }

    private fun updateUserPostNumber(popUpScreen: () -> Unit) {
        viewModelScope.launch(exceptionHandler) {
            userPostNum(email.toString()) { error ->
                if (error == null) {
                    SnackbarManager.showMessage(com.devyoung.base.R.string.postUpload)
                    popUpScreen()
                }
                else onError(error)
            }
        }
    }
}
