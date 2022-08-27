package com.devyoung.search.presentation.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.devyoung.base.InstaViewModel
import com.devyoung.base.Screen
import com.devyoung.base.SnackBarManager
import com.devyoung.search.domain.usecase.SearchUser
import com.devyoung.base.R.string as AppText
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

    private val searchText get() = searchState.value.searchText

    fun onSearchTextChanged(newValue: String){
        searchState.value = searchState.value.copy(
            userCardView = false ,
            searchText = newValue,
            profileImg = "",
            nickName = ""
        )
    }

    fun onSearchTextCleared(){
        searchState.value = searchState.value.copy(
            searchText = "",
            userCardView = false ,
            profileImg = "",
            circleLoading = false
        )
    }

    fun onSearch(){
        viewModelScope.launch(exceptionHandler) {
            searchState.value = searchState.value.copy(userCardView = false)
            searchState.value = searchState.value.copy(circleLoading = true)
            delay(3000)
            searchUser(
                userNickname = searchText,
                onError = {
                    SnackBarManager.showMessage(AppText.notExistUser)
                },
                onSuccess = { uri, userEmail ->
                    searchState.value = searchState.value.copy(profileImg = uri, userEmail = userEmail)
                    searchState.value = searchState.value.copy(nickName = searchText)
                    searchState.value = searchState.value.copy(userCardView = true)
                }
            )
            searchState.value = searchState.value.copy(circleLoading = false)

        }
    }

    fun onBack(popUpScreen : () -> Unit ){
        popUpScreen()
    }

    fun onUserClick(openScreen: (String)-> Unit, id: String){
        openScreen(Screen.User.passId(id))
    }
}