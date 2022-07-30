package com.devyoung.search.presentation.user

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.devyoung.base.InstaViewModel
import com.devyoung.search.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserInfo: GetUserInfo,
    private val getAllPosts: GetAllPosts,
    private val checkRequest: CheckRequest,
    private val getMyAccountEmail: GetMyAccountEmail,
    private val sendFollowRequest: SendFollowRequest,
    private val deleteFollowRequest: DeleteFollowRequest,
    private val updateFollowWaitingList: UpdateFollowWaitingList,
    private val deleteFollowWaitingList: DeleteFollowWaitingList
) : InstaViewModel() {

    var uiState = mutableStateOf(UserState())
        private set

    private var myEmail: String = ""

    fun initialize(personEmail: String) {
        viewModelScope.launch {
            myEmail = getMyAccountEmail()!!

            if(personEmail == myEmail){
                uiState.value = uiState.value.copy(searchMyself = true)
            }

            uiState.value = uiState.value.copy(screenLoading = true)

            delay(4000)

            getPersonInfo(personEmail)

            getPersonPosts(personEmail)

            checkWaitingList(personEmail)

            uiState.value = uiState.value.copy(screenLoading = false)
        }

    }

    private fun getPersonInfo(personEmail: String) {
        viewModelScope.launch(exceptionHandler) {
            getUserInfo(personEmail, ::onError) {
                uiState.value = uiState.value.copy(user = it)
            }
        }
    }

    private fun getPersonPosts(personEmail: String){
        viewModelScope.launch(exceptionHandler) {
            getAllPosts(userEmail = personEmail, ::onError) {
                uiState.value = uiState.value.copy(post = it)
            }
        }
    }

    private fun checkWaitingList(personEmail: String){
        viewModelScope.launch(Dispatchers.IO) {
            checkRequest(personEmail = personEmail, ::onError){ boolean ->
                Log.d(TAG, "checkRequest: $boolean")
                uiState.value = uiState.value.copy(hasRequest = boolean)
            }
        }
    }


   fun onFollowButtonClicked(personEmail: String){
       if(uiState.value.hasRequest) {
           uiState.value = uiState.value.copy(hasRequest = false)
           sendFollowRequest(personEmail)
           updateFollowWaitingList(personEmail)
       }
       else {
           uiState.value = uiState.value.copy(hasRequest = true)
           deleteFollowRequest(personEmail)
           deleteFollowWaitingList(personEmail)
       }
    }



    private fun sendFollowRequest(personEmail: String){
        viewModelScope.launch(exceptionHandler) {
               sendFollowRequest(personEmail){ error ->
                   if(error!=null) onError(error)
               }
           }
    }

    private fun deleteFollowRequest(personEmail: String){
        viewModelScope.launch(exceptionHandler) {
               deleteFollowRequest(personEmail){ error ->
                   if(error!=null) onError(error)
               }
           }
    }

    private fun updateFollowWaitingList(personEmail: String){
        viewModelScope.launch(exceptionHandler) {
            updateFollowWaitingList(personEmail){ error ->
                if(error!=null) onError(error)
            }
        }
    }

    private fun deleteFollowWaitingList(personEmail: String){
        viewModelScope.launch(exceptionHandler) {
            deleteFollowWaitingList(personEmail){ error ->
                if(error!=null) onError(error)
            }
        }
    }
}