package com.devyoung.instaclone.presentation

import androidx.lifecycle.ViewModel
import com.devyoung.instaclone.repository.HomeRepository
import com.devyoung.login.domain.repository.LoginRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel(){
}