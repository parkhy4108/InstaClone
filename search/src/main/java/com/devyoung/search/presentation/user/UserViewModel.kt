package com.devyoung.search.presentation.user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.devyoung.base.InstaViewModel
import com.devyoung.base.Screen
import com.devyoung.search.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    getMyAccountEmail: GetMyAccountEmail,
    private val getUserInfo: GetUserInfo,
    private val getAllPosts: GetAllPosts,
    private val checkRequest: CheckRequest,
    private val checkFollowingList: CheckFollowingList,
    private val sendFollowRequest: SendFollowRequest,
    private val deleteFollower: DeleteFollower,
    private val deleteFollowing: DeleteFollowing,
    private val deleteFollowRequest: DeleteFollowRequest,
    private val deleteFollowWaitingList: DeleteFollowWaitingList,
    private val updateFollowWaitingList: UpdateFollowWaitingList,
    private val updateFollowerNum: UpdateFollowerNum,
    private val updateFollowingNum: UpdateFollowingNum
) : InstaViewModel() {

    var userState = mutableStateOf(UserState())
        private set

    private val myEmail = getMyAccountEmail()!!

    fun getPersonInfo(personEmail: String) {
        if(personEmail == myEmail){
            userState.value = userState.value.copy(searchMyself = true)
        }
        viewModelScope.launch(exceptionHandler) {
            getUserInfo(personEmail, ::onError) {
                userState.value = userState.value.copy(user = it)
                checkWaitingList(personEmail)
            }
        }
    }

    fun getPersonPosts(personEmail: String){
        viewModelScope.launch(exceptionHandler) {
            getAllPosts(userEmail = personEmail, ::onError) {
                userState.value = userState.value.copy(post = it)

            }
        }
    }

    private fun checkWaitingList(personEmail: String){
        viewModelScope.launch(Dispatchers.IO) {
            checkRequest(email = myEmail, personEmail = personEmail, ::onError){ boolean ->
                userState.value = userState.value.copy(waiting = boolean)
                checkFollowingList(personEmail)
            }
        }
    }

    private fun checkFollowingList(personEmail: String){
        viewModelScope.launch(exceptionHandler){
            checkFollowingList(email = myEmail, personEmail = personEmail,::onError) { boolean ->
                userState.value = userState.value.copy(following = boolean)
            }
            userState.value = userState.value.copy(circleLoading = false)
        }
    }

   fun onFollowButtonClicked(personEmail: String, openScreen: (String) -> Unit){
       if(userState.value.waiting) {
           userState.value = userState.value.copy(waiting = false)
           deleteFollowRequest(personEmail)
           deleteFollowWaitingList(personEmail)
       }
       else if(userState.value.searchMyself){
           openScreen(Screen.EditProfile.route)
       }
       else if(userState.value.following){
           userState.value = userState.value.copy(openDialog = true)
       }
       else {
           userState.value = userState.value.copy(waiting = true)
           sendFollowRequest(personEmail)
           updateFollowWaitingList(personEmail)

       }
    }

    fun onDialogCancel(){
        userState.value = userState.value.copy(openDialog = false)
    }

    fun onDialogConfirmClicked(personEmail: String){
        deleteFollower(personEmail)
        updateFollowerNum(personEmail)
        deleteFollowing(personEmail)
        updateFollowingNum(myEmail)
        userState.value = userState.value.copy(openDialog = false)
        userState.value = userState.value.copy(following = false)
    }

    private fun sendFollowRequest(personEmail: String){
        viewModelScope.launch(exceptionHandler) {
               sendFollowRequest(email = myEmail, personEmail){ error ->
                   if(error!=null) onError(error)
               }
           }
    }

    private fun deleteFollowRequest(personEmail: String){
        viewModelScope.launch(exceptionHandler) {
               deleteFollowRequest(email = myEmail, personEmail){ error ->
                   if(error!=null) onError(error)
               }
           }
    }

    private fun updateFollowWaitingList(personEmail: String){
        viewModelScope.launch(exceptionHandler) {
            updateFollowWaitingList(email = myEmail, personEmail){ error ->
                if(error!=null) onError(error)
            }
        }
    }

    private fun updateFollowerNum(email: String) {
        viewModelScope.launch(exceptionHandler) {
            updateFollowerNum(email){ error ->
                if(error!=null) onError(error)
            }
        }
    }

    private fun updateFollowingNum(email: String) {
        viewModelScope.launch(exceptionHandler) {
            updateFollowingNum(email){ error ->
                if(error!=null) onError(error)
            }
        }
    }

    private fun deleteFollowWaitingList(personEmail: String){
        viewModelScope.launch(exceptionHandler) {
            deleteFollowWaitingList(email = myEmail, personEmail){ error ->
                if(error!=null) onError(error)
            }
        }
    }

    private fun deleteFollower(personEmail: String){
        viewModelScope.launch(exceptionHandler) {
            deleteFollower(email = myEmail, personEmail = personEmail){ error ->
                if(error!=null) onError(error)
            }
        }
    }

    private fun deleteFollowing(personEmail: String){
        viewModelScope.launch(exceptionHandler) {
            deleteFollowing(email = myEmail, personEmail = personEmail){ error ->
                if(error!=null) onError(error)
            }
        }
    }



}