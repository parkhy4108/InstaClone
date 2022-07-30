package com.devyoung.base

const val ARG_KEY = "id"
const val ARG_EMAIL = "email"
sealed class Screen(val route: String) {
    object Root: Screen(route = "root_Graph")
    object Authentication: Screen(route = "auth_Graph")
    object Login : Screen(route = "LOGIN")
    object Home: Screen(route = "Home")
        fun passEmail(email:String): String {
            return this.route.replace(
                oldValue = "{$ARG_EMAIL}",
                newValue = email
            )
        }
    object SignUp: Screen(route = "Sign Up")
    object Post: Screen(route = "Post")
    object First: Screen(route = "First")
    object SearchDetail: Screen(route = "SearchDetail")
    object User: Screen(route = "User/{$ARG_KEY}")
        fun passId(id: String): String{
            return this.route.replace(
                oldValue = "{$ARG_KEY}",
                newValue = id
            )
        }
    object FollowerRequest: Screen(route = "FollowerRequest")
}