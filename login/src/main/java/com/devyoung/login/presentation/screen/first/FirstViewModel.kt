package com.devyoung.login.presentation.screen.first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devyoung.base.*
import com.devyoung.login.domain.usecase.GetCurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    private val getCurrentUser: GetCurrentUser,
) : ViewModel(){

    fun appStart(openAndPopUp: (String, String)-> Unit){
        viewModelScope.launch {
            if(getCurrentUser()!=null) openAndPopUp(PROFILE_SCREEN, FIRST_SCREEN)
            else openAndPopUp(EMAIL_LOGIN_SCREEN, FIRST_SCREEN)
        }
    }
}