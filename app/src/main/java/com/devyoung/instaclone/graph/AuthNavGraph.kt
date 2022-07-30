package com.devyoung.instaclone.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.devyoung.base.Screen
import com.devyoung.instaclone.presentation.AppState
import com.devyoung.login.presentation.screen.emailLogin.LoginScreen
import com.devyoung.login.presentation.screen.signup.SignUpScreen

fun NavGraphBuilder.authNavGraph(appState: AppState){
    navigation(
        route = Screen.Authentication.route,
        startDestination = Screen.Login.route
    ){
        composable(route = Screen.Login.route){
            LoginScreen(
                openScreen = { route -> appState.navController.navigate(route) },
                openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) }
            )
        }
        composable(route = Screen.SignUp.route){
            SignUpScreen(
                openAndPopUp = { route , popUp -> appState.navigateAndPopUp(route, popUp)}
            )
        }
    }
}
