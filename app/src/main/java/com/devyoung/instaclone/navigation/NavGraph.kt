package com.devyoung.instaclone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devyoung.base.NavItems
import com.devyoung.instaclone.presentation.Home
import com.devyoung.login.presentation.login.FaceBookLoginScreen
import com.devyoung.login.presentation.login.LoginScreen
import com.devyoung.login.presentation.signup.SignUpScreen
import com.devyoung.profile.ProfileScreen


@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String
){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(NavItems.Login.route){
            LoginScreen(navController = navController)
        }
        composable(NavItems.SignUp.route){
            SignUpScreen(navController = navController)
        }
        composable(NavItems.FaceBookLogin.route){
            FaceBookLoginScreen()
        }
        composable(NavItems.Home.route){
            Home()
        }
        composable(NavItems.Profile.route){
            ProfileScreen()
        }
    }
}







