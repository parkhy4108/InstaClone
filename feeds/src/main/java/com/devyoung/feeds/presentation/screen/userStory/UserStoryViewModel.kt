package com.devyoung.feeds.presentation.screen.userStory

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.devyoung.base.InstaViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserStoryViewModel @Inject constructor() : InstaViewModel() {

    val state = mutableStateOf(UserStoryState())

    fun popUpScreen(popUpScreen: ()->Unit) {
        viewModelScope.launch {
            delay(5000)
            popUpScreen()
        }
    }

    fun onProgressChanged(newValue: Float) {
        state.value = state.value.copy(progress = newValue)
    }

}
