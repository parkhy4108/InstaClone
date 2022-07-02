package com.devyoung.base

sealed class Screen(var route:String, var description: String) {
    object Login : Screen("login", description = "EmailLogin" )
    object SignUp : Screen("signUp",description =  "SignUp")
    object FaceBookLogin : Screen("FaceBookLogin",description = "FacebookLogin")
    object Home : Screen("home",description =  "Home")
    object Feed : Screen("feed",description =  "Feeds")
    object Profile : Screen("profile",description =  "Profile")
}