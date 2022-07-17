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
    fun appStart(openAndPopUp: (String)-> Unit){
        Log.d(TAG, "appStart")
        if(hasUser()) {
            Log.d(TAG, "현재 회원 있음")
            openAndPopUp(HOME)

        }
        else {
            Log.d(TAG, "현재 회원 없음")
            openAndPopUp(AUTHENTICATION)
        }
    }
}