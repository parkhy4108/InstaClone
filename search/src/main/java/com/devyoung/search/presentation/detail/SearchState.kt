package com.devyoung.search.presentation.detail

import android.net.Uri

data class SearchState(
    val searchText: String = "",
    val loading : Boolean = false,
    val profileImg: String = "",
    val view: Boolean = false,
    val nickName : String = "",
    val userEmail : String = "no"
)
