package com.devyoung.search.presentation.detail

data class SearchState(
    val searchText: String = "",
    val circleLoading : Boolean = false,
    val profileImg: String = "",
    val userCardView: Boolean = false,
    val nickName : String = "",
    val userEmail : String = "no"
)
