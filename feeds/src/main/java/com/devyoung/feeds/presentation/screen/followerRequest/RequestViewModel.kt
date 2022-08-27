package com.devyoung.feeds.presentation.screen.followerRequest

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.devyoung.base.InstaViewModel
import com.devyoung.feeds.data.model.User
import com.devyoung.feeds.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor(
    getUserEmail: GetUserEmail,
    private val checkMyFollowerList: CheckMyFollowerList,
    private val checkMyFollowingList: CheckMyFollowingList,
    private val checkMyWaitingList: CheckMyWaitingList,
    private val deleteMyEmailInUserWaitingList: DeleteMyEmailInUserWaitingList,
    private val deleteUserEmailInMyWaitingList: DeleteUserEmailInMyWaitingList,
    private val deleteRequestInUserList: DeleteRequestInUserList,
    private val deleteRequestInMyList: DeleteRequestInMyList,
    private val loadMyRequestedList: LoadMyRequestedList,
    private val updateMyFollowerList: UpdateMyFollowerList,
    private val updateUserFollowingList: UpdateUserFollowingList,
    private val updateMyWaitingList: UpdateMyWaitingList,
    private val updateFollowingNum: UpdateFollowingNum,
    private val updateFollowerNum: UpdateFollowerNum,
    private val updateStoryList: UpdateStoryList,
    private val sendRequestToUser: SendRequestToUser,

    ) : InstaViewModel() {

    var requestState = mutableStateOf(RequestState())
        private set

    var userListState = mutableStateListOf<User>()
        private set

    var isSelectedState = mutableStateMapOf<String, Int>()
        private set

    private val email = getUserEmail()

    fun loadMyRequestedList() {
        viewModelScope.launch {
            requestState.value = requestState.value.copy(loading = true)
            delay(3000)
            loadMyRequestedList(email.toString() ,::onError) { result ->
                requestState.value = requestState.value.copy(followerList = result)
                for(document in result){
                    userListState.add(document)
                    checkMyFollowingList(document.userEmail)
                }
            }
            requestState.value = requestState.value.copy(loading = false)
        }

    }


    fun onDelete(user: User) {
        userListState.remove(user)
        isSelectedState.remove(user.userEmail)
        deleteRequestInMyList(user.userEmail)
        deleteMyEmailInUserWaitingList(user.userEmail)
    }

    fun onBack(popUpScreen: () -> Unit) {
        popUpScreen()
    }

    fun onFollowButtonClicked(user: User) {
        if (isSelectedState[user.userEmail]==0) {
            isSelectedState[user.userEmail]=1
            deleteMyEmailInUserWaitingList(user.userEmail)
            updateUserFollowingList(user.userEmail)
            updateMyFollowerList(user.userEmail)
            updateFollowerNum(email.toString())
            updateFollowingNum(user.userEmail)
            updateStoryList(user.userEmail)
        }
        else if(isSelectedState[user.userEmail]==1) {
            isSelectedState[user.userEmail]=2
            sendRequestToUser(user.userEmail)
            updateMyWaitingList(user.userEmail)
        }
        else if(isSelectedState[user.userEmail]==2){
            isSelectedState[user.userEmail]=1
            deleteRequestInUserList(user.userEmail)
            deleteUserEmailInMyWaitingList(user.userEmail)
        }
        else {
            deleteRequestInMyList(user.userEmail)
            deleteRequestInUserList(user.userEmail)
            deleteMyEmailInUserWaitingList(user.userEmail)
            updateFollowerNum(email.toString())
            updateFollowingNum(user.userEmail)
            updateUserFollowingList(user.userEmail)
            updateStoryList(user.userEmail)
            userListState.remove(user)
            isSelectedState.remove(user.userEmail)
        }
    }

    private fun deleteRequestInUserList(personEmail: String){
        viewModelScope.launch(exceptionHandler) {
            Log.d(TAG, "myEmail: ${email.toString()}, personEmail: $personEmail")
            deleteRequestInUserList(myEmail = email.toString(), personEmail = personEmail ) { error ->
                if(error!=null) onError(error)
            }
        }
    }

    private fun deleteRequestInMyList(personEmail: String) {
        viewModelScope.launch(exceptionHandler) {
            deleteRequestInMyList(email.toString(), personEmail){ error ->
                if (error != null) onError(error)
            }
        }
    }

    private fun updateMyFollowerList(personEmail: String) {
        viewModelScope.launch(exceptionHandler) {
            updateMyFollowerList(email.toString(), personEmail){ error ->
                if (error != null) onError(error)
            }
        }
    }

    private fun deleteMyEmailInUserWaitingList(personEmail: String) {
        viewModelScope.launch(exceptionHandler) {
            deleteMyEmailInUserWaitingList(email.toString(), personEmail){ error ->
                if (error != null) onError(error)
            }
        }
    }

    private fun deleteUserEmailInMyWaitingList(personEmail: String) {
        viewModelScope.launch(exceptionHandler) {
            deleteUserEmailInMyWaitingList(email.toString(), personEmail){ error ->
                if (error != null) onError(error)
            }
        }
    }

    private fun updateUserFollowingList(personEmail: String) {
        viewModelScope.launch(exceptionHandler) {
            updateUserFollowingList(
                myEmail = email.toString(),
                personEmail = personEmail
            ){ error ->
                if (error != null) onError(error)
            }
        }
    }

    private fun updateMyWaitingList(personEmail: String){
        viewModelScope.launch {
            updateMyWaitingList(email.toString(), personEmail){ error ->
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

    private fun updateStoryList(personEmail: String){
        viewModelScope.launch(exceptionHandler) {
            if (email != null) {
                updateStoryList(email, personEmail){ error ->
                    if(error!=null) onError(error)
                }
            }
        }
    }

    private fun sendRequestToUser(personEmail: String){
        viewModelScope.launch {
            sendRequestToUser(email.toString(), personEmail){ error ->
                if(error != null )onError(error)
            }
        }
    }


    private fun checkMyFollowerList(personEmail: String){
        viewModelScope.launch(exceptionHandler){
            checkMyFollowerList(email.toString(), personEmail, ::onError){ follower ->
                when(follower){
                    true -> {
                        checkMyWaitingList(personEmail)
                    }
                    false -> {
                        isSelectedState[personEmail] = 0
                        }
                    }
                }
            }
        }

    private fun checkMyFollowingList(personEmail: String){
        viewModelScope.launch(exceptionHandler){
            checkMyFollowingList(email.toString(), personEmail, ::onError){ following ->
                when(following){
                    true -> {
                        isSelectedState[personEmail] = 3
                    }
                    false -> {
                        checkMyFollowerList(personEmail)
                    }
                }
            }
        }
    }

    private fun checkMyWaitingList(personEmail: String){
        viewModelScope.launch(exceptionHandler) {
            checkMyWaitingList(email.toString(), personEmail, ::onError) { waiting ->
                when (waiting) {
                    true -> {
                        isSelectedState[personEmail] = 2
                    }
                    false -> {
                        isSelectedState[personEmail] = 1
                    }
                }
            }
        }
    }

}


