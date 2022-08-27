package com.devyoung.search.presentation.user

import com.devyoung.search.data.model.User

data class UserState(
    val post: List<String> = emptyList(),
    val user: User? = null,
    val circleLoading: Boolean = false,
    val buttonLoading: Boolean = false,
    val waiting: Boolean = false,
    val searchMyself: Boolean = false,
    val following: Boolean = false,
    val openDialog: Boolean = false
)