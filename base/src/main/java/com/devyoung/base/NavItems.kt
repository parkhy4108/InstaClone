package com.devyoung.base

sealed class NavItems(var route:String, var description: String) {
    object Login : NavItems("login","Login")
    object SignUp : NavItems("signUp", "SignUp")
    object FaceBookLogin : NavItems("FaceBookLogin", "FaceBookLogin")
    object Home : NavItems("Home", "Home")
    object Feeds : NavItems("feeds", "Feeds")
    object Profile : NavItems("profile", "Profile")
}