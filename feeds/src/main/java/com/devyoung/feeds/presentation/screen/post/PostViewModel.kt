package com.devyoung.feeds.presentation.screen.post

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.devyoung.base.InstaViewModel
import com.devyoung.feeds.data.model.Post
import com.devyoung.feeds.domain.usecase.GetUserEmail
import com.devyoung.feeds.domain.usecase.SavePost
import com.devyoung.feeds.domain.usecase.UpdatePostNum
import com.devyoung.feeds.domain.usecase.UploadFile
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
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


    fun onImageChange(newImageUri: Uri?){
        postState.value = postState.value.copy(imageUrl = newImageUri)
    }

    fun onCommentsChange(newComments: String) {
        postState.value = postState.value.copy(comments = newComments)
    }

    fun onSavePostClick(
        popUpScreen : () -> Unit
    ) {
        val postId = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault())
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val time = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val currentTime : Long = System.currentTimeMillis()
        viewModelScope.launch(exceptionHandler) {
            val userEmail = getUserEmail()
            savePost(
                post = Post(
                    postId = postId.format(currentTime).toString(),
                    postImg = imageUri.toString(),
                    userId = userEmail.toString(),
                    date = date.format(currentTime).toString(),
                    time = time.format(currentTime).toString(),
                    comments = comments,
                    like = 0)) { error ->
                if (error == null) {
                    updateUserPostNumber(userEmail.toString(), popUpScreen)
                    if (userEmail != null) {
                        uploadFile(userEmail)
                    }
                }
                else onError(error)
            }
        }
    }

    private fun updateUserPostNumber(userEmail: String, popUpScreen: () -> Unit) {
        viewModelScope.launch(exceptionHandler) {
            userPostNum(userEmail) { error ->
                if (error != null) onError(error)
                else popUpScreen()
            }
        }
    }

    private fun uploadFile(userEmail: String) {
        viewModelScope.launch(exceptionHandler) {
            imageUri?.let { uri ->
                uploadFile(userEmail, uri){ error ->
                    if (error != null) onError(error)
                }
            }
        }
    }





}
