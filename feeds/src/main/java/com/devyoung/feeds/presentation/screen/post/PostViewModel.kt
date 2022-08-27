package com.devyoung.feeds.presentation.screen.post

import android.net.Uri
import androidx.compose.runtime.*
import androidx.core.net.toUri
import androidx.lifecycle.viewModelScope
import com.devyoung.base.InstaViewModel
import com.devyoung.base.SnackBarManager
import com.devyoung.feeds.data.model.Post
import com.devyoung.feeds.domain.usecase.GetUserEmail
import com.devyoung.feeds.domain.usecase.SavePost
import com.devyoung.feeds.domain.usecase.UpdatePostNum
import com.devyoung.feeds.domain.usecase.UploadFile
import com.devyoung.base.R.string as AppText
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun onImageChanged(newImageUri: Uri?, popUpScreen: () -> Unit) {
        if(newImageUri == null){
            popUpScreen()
        }
        postState.value = postState.value.copy(imageUrl = newImageUri)
    }

    fun onCommentsChanged(newComments: String) {
        postState.value = postState.value.copy(comments = newComments)
    }

    fun onSavePostClick() {
        postState.value = postState.value.copy(openDialog = true)
    }

    fun onDialogConfirmClick(
        popUpScreen: () -> Unit,
    ) {
        uploadFile(imageUri.toString(), popUpScreen)
    }

    fun onDialogCancel(){
        postState.value = postState.value.copy(openDialog = false)
    }

    fun onBackButtonClicked(
        popUpScreen: () -> Unit
    ) {
        popUpScreen()
    }

    private fun uploadFile(
        uri: String,
        popUpScreen: () -> Unit
    ) {
        val postIdForm = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
        val dateForm = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val timeForm = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val currentTime: Long = System.currentTimeMillis()
        postState.value = postState.value.copy(openDialog = false)
        postState.value = postState.value.copy(circleLoading = true)
        viewModelScope.launch(exceptionHandler) {
            uploadFile(
                myEmail = email.toString(),
                postId = postIdForm.format(currentTime).toString(),
                uri = uri.toUri(),
                onError =  ::onError) { url ->
                viewModelScope.launch ( exceptionHandler ) {
                    savePost(
                        myEmail = email.toString(),
                        post = Post(
                            postId = postIdForm.format(currentTime).toString(),
                            postImg = url,
                            userId = email.toString(),
                            date = dateForm.format(currentTime).toString(),
                            time = timeForm.format(currentTime).toString(),
                            comments = comments,
                            like = "0"
                        )
                    ){ error ->
                        if (error != null) {
                            postState.value = postState.value.copy(circleLoading = false)
                            onError(error)
                        }
                        else {
                            updateUserPostNumber(popUpScreen)
                        }
                    }
                }
            }
        }
    }

    private fun updateUserPostNumber(popUpScreen: () -> Unit) {
        viewModelScope.launch(exceptionHandler) {
            userPostNum(email.toString()) { error ->
                if (error == null) {
                    postState.value = postState.value.copy(circleLoading = false)
                    SnackBarManager.showMessage(AppText.postComplete)
                    popUpScreen()
                }
                else {
                    postState.value = postState.value.copy(circleLoading = false)
                    onError(error)
                }
            }
        }
    }
}
