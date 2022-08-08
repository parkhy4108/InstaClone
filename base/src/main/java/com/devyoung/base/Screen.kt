package com.devyoung.base

const val ARG_KEY = "id"
const val ARG_EMAIL = "email"
sealed class Screen(val route: String) {
    object Login : Screen(route = "LOGIN")
    object SignUp: Screen(route = "Sign Up")
    object Post: Screen(route = "Post")
    object First: Screen(route = "First")
    object SearchDetail: Screen(route = "SearchDetail")
    object User: Screen(route = "User/{$ARG_KEY}")
    object FollowerRequest: Screen(route = "FollowerRequest")

    fun passId(id: String): String{
        return this.route.replace(
            oldValue = "{$ARG_KEY}",
            newValue = id
        )
    }
}