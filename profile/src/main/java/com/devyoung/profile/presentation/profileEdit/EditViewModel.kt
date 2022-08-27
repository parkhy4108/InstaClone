package com.devyoung.profile.presentation.profileEdit

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.devyoung.base.InstaViewModel
import com.devyoung.base.SnackBarManager
import com.devyoung.profile.domain.usecase.*
import com.devyoung.base.R.string as AppText
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    getUserEmail: GetUserEmail,
    private val getUserInfo: GetUserInfo,
    private val updateNickName: UpdateNickName,
    private val updateProfileImg: UpdateProfileImg,
    private val checkNickName: CheckNickName
) : InstaViewModel() {

    var editState = mutableStateOf(EditState())
        private set

    private val userEmail = getUserEmail()

    private val imageUri
        get() = editState.value.imageUrl

    private val nickName
        get() = editState.value.nickName

    var userImage = ""

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
        editState.value = editState.value.copy(isNickNameExist = false)
        editState.value = editState.value.copy(nickName = newNickName)
    }

    fun onDialogOpen(){
        editState.value = editState.value.copy(openDialog = true)
    }

    fun onConfirmClicked(){
        viewModelScope.launch(exceptionHandler) {
            editState.value = editState.value.copy(circleLoading = true)
            editState.value = editState.value.copy(openDialog = false)
            delay(3000)
            if((nickName == editState.value.oldNickName)&&(userImage != imageUri)){
                updateProfileImg()
            }
            else if((nickName != editState.value.oldNickName)&&(userImage == imageUri)) {
                checkNickName()
            }
            else {
                updateBoth()
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
        viewModelScope.launch(Dispatchers.IO){
            checkNickName(
                nickName = nickName,
                onError = ::onError
            ){ boolean ->
                if(boolean) {
                    editState.value = editState.value.copy(isNickNameExist = true)
                    SnackBarManager.showMessage(AppText.wrongNickName)
                }else {
                    updateNickName()
                }
            }
        }
    }

    private fun updateNickName(){
        viewModelScope.launch(exceptionHandler){
            updateNickName(
                email = userEmail.toString(),
                oldNickName = editState.value.oldNickName,
                newNickName = nickName,
            ) { error ->
                if(error != null) {
                    editState.value = editState.value.copy(circleLoading = false)
                    onError(error)
                }
                else {
                    editState.value = editState.value.copy(oldNickName = nickName)
                    editState.value = editState.value.copy(circleLoading = false)
                    SnackBarManager.showMessage(AppText.profileChange)
                }
            }
        }
    }

    private fun updateProfileImg(){
        viewModelScope.launch(exceptionHandler){
            updateProfileImg(email = userEmail.toString(), image = imageUri){ error ->
                if(error != null) {
                    editState.value = editState.value.copy(circleLoading = false)
                    onError(error)
                }
                else {
                    userImage = imageUri
                    editState.value = editState.value.copy(circleLoading = false)
                    SnackBarManager.showMessage(AppText.profileChange)
                }
            }
        }
    }

    private fun updateBoth(){
        viewModelScope.launch(exceptionHandler){
            checkNickName(
                nickName = nickName,
                onError = ::onError
            ){ boolean ->
                if(boolean) {
                    editState.value = editState.value.copy(isNickNameExist = true)
                    SnackBarManager.showMessage(AppText.wrongNickName)
                }else {
                    viewModelScope.launch(Dispatchers.IO) {
                        updateNickName(
                            email = userEmail.toString(),
                            oldNickName = editState.value.oldNickName,
                            newNickName = nickName,
                        ) { error ->
                            if(error != null) {
                                editState.value = editState.value.copy(circleLoading = false)
                                onError(error)
                            }
                            else {
                                viewModelScope.launch(exceptionHandler){
                                    updateProfileImg(email = userEmail.toString(), image = imageUri){ error ->
                                        if(error != null) {
                                            editState.value = editState.value.copy(circleLoading = false)
                                            onError(error)
                                        }
                                        else {
                                            userImage = imageUri
                                            editState.value = editState.value.copy(circleLoading = false)
                                            SnackBarManager.showMessage(AppText.profileChange)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}