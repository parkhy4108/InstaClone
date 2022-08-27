package com.devyoung.profile.presentation.follower

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.devyoung.base.InstaViewModel
import com.devyoung.base.SnackBarManager
import com.devyoung.profile.domain.usecase.*
import com.devyoung.base.R.string as AppText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowerListViewModel @Inject constructor(
    getUserEmail: GetUserEmail,
    private val getUserInfo: GetUserInfo,
    private val updateNickName: UpdateNickName,
    private val updateProfileImg: UpdateProfileImg,
    private val checkNickName: CheckNickName
) : InstaViewModel() {

    var editState = mutableStateOf(FollowerState())
        private set

    private val userEmail = getUserEmail()

    private val imageUri
        get() = editState.value.imageUrl

    private val nickName
        get() = editState.value.nickName

    private var userImage = ""

    fun getUserInfo(){
        viewModelScope.launch(exceptionHandler) {
            getUserInfo(email = userEmail.toString(), ::onError){
                editState.value = editState.value.copy(imageUrl = it.userImage)
                editState.value = editState.value.copy(oldNickName = it.userNickName)
                editState.value = editState.value.copy(nickName = it.userNickName)
                editState.value = editState.value.copy(email = it.userEmail)
                userImage = it.userImage
            }
        }
    }

    fun onImageChanged(newImageUri: Uri?) {
        editState.value = editState.value.copy(imageUrl = newImageUri.toString())
    }

    fun onNickNameChanged(newNickName: String) {
        editState.value = editState.value.copy(check = false)
        editState.value = editState.value.copy(nickName = newNickName)
    }

    fun onDialogOpen(){
        editState.value = editState.value.copy(openDialog = true)
    }

    fun onConfirmClicked(){
        viewModelScope.launch(exceptionHandler) {
            if((nickName == editState.value.oldNickName)&&(userImage != imageUri)){
                updateProfileImg()
            }
            else  {
                checkNickName()
                if(editState.value.imageUrl != userImage){
                    updateProfileImg()
                }
            }
        }
    }

    fun onDialogCancel(){
        editState.value = editState.value.copy(openDialog = false)
    }


    fun onBackButtonClicked(popUpScreen: ()->Unit) {
        popUpScreen()
    }

    private fun checkNickName(){
        viewModelScope.launch(exceptionHandler){
            checkNickName(
                nickName = nickName,
                onError = ::onError
            ){ boolean ->
                Log.d(TAG, "checkNickName: $boolean")
                if(boolean) {
                    editState.value = editState.value.copy(check = true)
                    editState.value = editState.value.copy(openDialog = false)
                    SnackBarManager.showMessage(AppText.wrongNickName)
                }else {
                    updateNickName()
                }
            }
        }
    }

    private fun updateNickName(){
        viewModelScope.launch(exceptionHandler){
            editState.value = editState.value.copy(isDisplayed = true)
            editState.value = editState.value.copy(openDialog = false)
            delay(3000)
            updateNickName(
                email = userEmail.toString(),
                oldNickName = editState.value.oldNickName,
                newNickName = nickName,
            ) { error ->
                if(error != null) {
                    onError(error)
                    editState.value = editState.value.copy(isDisplayed = false)
                }
                else {
                    editState.value = editState.value.copy(isDisplayed = false)
                    SnackBarManager.showMessage(AppText.profileChange)
                }
            }
        }
    }

    private fun updateProfileImg(){
        viewModelScope.launch(exceptionHandler){
            editState.value = editState.value.copy(isDisplayed = true)
            editState.value = editState.value.copy(openDialog = false)
            delay(3000)
            updateProfileImg(email = userEmail.toString(), image = imageUri,){ error ->
                if(error != null) onError(error)
                else {
                    editState.value = editState.value.copy(isDisplayed = false)
                    SnackBarManager.showMessage(AppText.profileChange)
                }
            }
        }
    }
}