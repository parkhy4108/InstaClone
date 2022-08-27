package com.devyoung.login.presentation.screen.first

import com.devyoung.base.*
import com.devyoung.login.domain.usecase.GetUserEmail
import com.devyoung.login.domain.usecase.HasUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    private val hasUser: HasUser,
) : InstaViewModel() {
    fun appStart(openAndPopUp: (String, String)-> Unit, navigateBottomBar: (String)->Unit){
        if(hasUser()) {
            navigateBottomBar(BottomBarScreen.Feed.route)
        }
        else {
            openAndPopUp(Screen.Login.route, Screen.First.route)
        }
    }
}