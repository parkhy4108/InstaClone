package com.devyoung.instaclone.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.devyoung.base.AUTHENTICATION
import com.devyoung.instaclone.presentation.composable.BottomBarScreen
import com.devyoung.login.presentation.screen.emailLogin.LoginScreen
import com.devyoung.login.presentation.screen.signup.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController){
    navigation(
        route = AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ){
        composable(route = AuthScreen.Login.route){
            LoginScreen(
//                openAndPopUp = { appState.navigateAndPopUp(Graph.HOME, AuthScreen.Login.route) },
//                openScreen = { appState.navigate(AuthScreen.SignUp.route) })

                openScreen = { navController.navigate(AuthScreen.SignUp.route) },
                openAndPopUp = {
                    navController.popBackStack()
//                    navController.navigate(Graph.HOME)
                    navController.navigate(BottomBarScreen.Feed.route)

                }
            )
        }
        composable(route = AuthScreen.SignUp.route){
            SignUpScreen(
                openAndPopUp = {
                    navController.popBackStack()
                    navController.navigate(BottomBarScreen.Feed.route)
                }
            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
}