package com.devyoung.login.presentation.screen.first

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.devyoung.base.*
import com.devyoung.login.domain.usecase.HasUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    private val hasUser: HasUser,
) : InstaViewModel() {
    fun appStart(openAndPopUp: (String, String)-> Unit){
        if(hasUser()) {
            openAndPopUp(Screen.Home.route, Screen.First.route)
        }
        else {
            openAndPopUp(Screen.Authentication.route, Screen.First.route)
        }
    }
}