package com.devyoung.feeds.presentation.screen.followerRequest

import android.content.ContentValues.TAG
import android.util.Log
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
    private val getRequest: GetRequest,
    private val checkWaitingList: CheckRequest,
    private val deleteRequest: DeleteRequest,
    private val updateFollower: UpdateFollower,
    private val deleteFollowWaitingList: DeleteWaitingList,
    private val updateFollowing: UpdateFollowing,
    private val sendFollowRequest: SendFollowRequest,
    private val updateFollowWaitingList: UpdateFollowWaitingList,
    private val checkFollowerList: CheckFollowerList
) : InstaViewModel() {

    var requestState = mutableStateOf(RequestState())
        private set

    var mapState : MutableMap<String,Int> = mutableStateMapOf()
        private set
    private val requestMap
        get() = requestState.value.requestMap

    private val map = mutableMapOf<String, Int>()

    fun loadFollowerRequest() {
        viewModelScope.launch {
            requestState.value = requestState.value.copy(loading = true)
            delay(3000)
            getRequest(::onError) { result ->
                Log.d(TAG, "loadFollowerRequest: $result")
                requestState.value = requestState.value.copy(followerList = result)
                for(document in result){
                    Log.d(TAG, "loadFollowerRequest: document ${document.userEmail}")
                    checkFollowerList(document.userEmail)
                }
            }
            requestState.value = requestState.value.copy(loading = false)
        }
    }

    fun onAccept(personEmail: String) {
        map.replace(personEmail,1)
        Log.d(TAG, "onAccept: $map")
//        deleteFollowWaitingList(personEmail)
//        updateFollowing(personEmail)
//        updateFollower(personEmail)
        requestState.value = requestState.value.copy(requestMap = map)
    }

    fun onDelete(personEmail: String) {
        deleteFollowRequest(personEmail)
        deleteFollowWaitingList(personEmail)
    }

    fun onBack(popUpScreen: () -> Unit) {
        popUpScreen()
    }

    fun onFollowButtonClicked(personEmail: String) {
        if (requestMap[personEmail]==1){
            Log.d(TAG, "onFollowButtonClicked: ${requestMap[personEmail]}")
            map.replace(personEmail, 2)
            requestState.value = requestState.value.copy(requestMap = map)
//            sendFollowRequest(personEmail)
//            updateFollowWaitingList(personEmail)
        }
        else {
            Log.d(TAG, "onFollowButtonClicked: ${requestMap[personEmail]}")
            map.replace(personEmail, 1)
            requestState.value = requestState.value.copy(requestMap = map)
//            deleteFollowWaitingList(personEmail)
        }
    }

    private fun deleteFollowRequest(personEmail: String) {
        viewModelScope.launch(exceptionHandler) {
            deleteRequest(personEmail){ error ->
                if (error != null) {
                    onError(error)
                }
            }
        }
    }

    private fun updateFollower(personEmail: String) {
        viewModelScope.launch(exceptionHandler) {
            updateFollower(personEmail){ error ->
                if (error != null) {
                    onError(error)
                }
            }
        }
    }

    private fun deleteFollowWaitingList(personEmail: String) {
        viewModelScope.launch(exceptionHandler) {
            deleteFollowWaitingList(personEmail){ error ->
                if (error != null) {
                    onError(error)
                }
            }
        }
    }

    private fun updateFollowing(personEmail: String) {
        viewModelScope.launch(exceptionHandler) {
            updateFollowing(
                personEmail = personEmail
            ){ error ->
                if (error != null) {
                    onError(error)
                }
            }
        }
    }

    private fun sendFollowRequest(personEmail: String){
        viewModelScope.launch {
            sendFollowRequest(personEmail){ error ->
                if(error != null ){
                    onError(error)
                }
            }
        }
    }

    private fun updateFollowWaitingList(personEmail: String){
        viewModelScope.launch {
            updateFollowWaitingList(personEmail){ error ->
                if(error!=null){
                    onError(error)
                }
            }
        }
    }


    private fun checkFollowerList(personEmail: String){
        viewModelScope.launch(exceptionHandler){
            checkFollowerList(personEmail = personEmail, ::onError){ follower ->
                Log.d(TAG, "follower: $personEmail  ---->  $follower")
                when(follower){
                    true -> {
                        checkWaitingList(personEmail)
                    }
                    false -> {
                        map[personEmail] = 0
                        Log.d(TAG, "map -----> $map")
                        requestState.value = requestState.value.copy(
                            requestMap = map
                        )
                        mapState[personEmail]=0
                        Log.d(TAG, "mutableStateMap: ${mapState[personEmail]}")
                        if(requestState.value.requestMap.size == requestState.value.followerList.size ){
                            requestState.value = requestState.value.copy(view = true)
                        }
                        Log.d(TAG, "reqeustState.value.map ----> ${requestState.value.requestMap}")
                    }
                }
            }
        }
    }

    private fun checkWaitingList(personEmail: String){
        viewModelScope.launch(exceptionHandler) {
            checkWaitingList(personEmail = personEmail, ::onError) { waiting ->
                Log.d(TAG, "waiting: $waiting")
                when (waiting) {
                    true -> {
                        map[personEmail] = 2
                        mapState[personEmail]=2
                        Log.d(TAG, "mapState: ${mapState[personEmail]}")
                        Log.d(TAG, "reqeustState.value.map ----> ${requestState.value.requestMap}")
                    }
                    false -> {
                        map[personEmail] = 1
                        mapState[personEmail]=1
                        Log.d(TAG, "mapState: ${mapState[personEmail]}")
                        Log.d(TAG, "map -----> $map")
                        Log.d(TAG, "reqeustState.value.map ----> ${requestState.value.requestMap}")
                    }
                }
                requestState.value = requestState.value.copy(
                    requestMap = map
                )
                if(requestState.value.requestMap.size == requestState.value.followerList.size ){
                    requestState.value = requestState.value.copy(view = true)
                }
            }
        }
    }

}