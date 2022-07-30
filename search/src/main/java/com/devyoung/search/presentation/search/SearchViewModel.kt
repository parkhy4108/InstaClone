package com.devyoung.search.presentation.search

import com.devyoung.base.InstaViewModel
import com.devyoung.base.Screen

import com.devyoung.search.domain.usecase.SearchUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

) : InstaViewModel() {


    fun onSearchClick(openScreen: (String) -> Unit ) {
        openScreen(Screen.SearchDetail.route)
    }
}