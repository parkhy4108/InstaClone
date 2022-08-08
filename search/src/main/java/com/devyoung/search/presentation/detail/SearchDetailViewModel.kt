package com.devyoung.search.presentation.detail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.devyoung.base.InstaViewModel
import com.devyoung.base.Screen
import com.devyoung.search.domain.usecase.SearchUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchDetailViewModel @Inject constructor(
    private val searchUser: SearchUser
) : InstaViewModel() {

    var searchState = mutableStateOf(SearchState())
        private set

    private val searchText
        get() = searchState.value.searchText


    fun onSearchTextChanged(newValue: String){
        searchState.value = searchState.value.copy(searchText = newValue, view = false , profileImg = "")
    }

    fun onSearchTextCleared(){
        searchState.value = searchState.value.copy(searchText = "", view = false , profileImg = "")
    }

    fun onSearch(){
        viewModelScope.launch(exceptionHandler) {
            searchState.value = searchState.value.copy(view = false)
            searchState.value = searchState.value.copy(loading = true)
            searchState.value = searchState.value.copy(nickName = searchText)
            delay(5000)
            searchUser(
                searchText,
                onError = { error, userEmail ->
                    onError(error)
                    searchState.value = searchState.value.copy(
                        userEmail = userEmail
                    )
                },
                onSuccess = { uri, userEmail ->
                    searchState.value = searchState.value.copy(
                        profileImg = uri,
                        userEmail = userEmail
                    )
                }
            )
            searchState.value = searchState.value.copy(loading = false)
            searchState.value = searchState.value.copy(view = true)
        }
    }

    fun onBack(popUpScreen : () -> Unit ){
        popUpScreen()
    }

    fun onUserClick(openScreen: (String)-> Unit, id: String){
        openScreen(Screen.User.passId(id))
    }


}