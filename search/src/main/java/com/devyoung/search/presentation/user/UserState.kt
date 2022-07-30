package com.devyoung.search.presentation.user

import com.devyoung.search.data.MyInfo
import com.devyoung.search.data.User

data class UserState(
    val post: ArrayList<String>? = null,
    val user: User? = null,
    val screenLoading: Boolean = false,
    val buttonLoading: Boolean = false,
    val hasRequest: Boolean = true,
    val searchMyself: Boolean = false
)