package com.devyoung.feeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getUser: GetUser
) : ViewModel() {

    fun getUserInfo() {
        viewModelScope.launch {
            getUser
        }
    }
}