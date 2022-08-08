package com.devyoung.search.presentation.user

import com.devyoung.search.data.MyInfo
import com.devyoung.search.data.User

data class UserState(
    val post: List<String> = emptyList(),
    val user: User? = null,
    val screenLoading: Boolean = false,
    val buttonLoading: Boolean = false,
    val waiting: Boolean = false,
    val searchMyself: Boolean = false,
    val following: Boolean = false,
    val openDialog: Boolean = false
)