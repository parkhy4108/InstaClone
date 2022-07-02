package com.devyoung.instaclone.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devyoung.base.*
import com.devyoung.feeds.presentation.screen.FeedScreen
import com.devyoung.instaclone.presentation.AppState
import com.devyoung.login.presentation.screen.emailLogin.LoginScreen
import com.devyoung.login.presentation.screen.facebookLogin.FaceBookLoginScreen
import com.devyoung.login.presentation.screen.first.FirstScreen
import com.devyoung.login.presentation.screen.signup.SignUpScreen
import com.devyoung.profile.presentation.ProfileScreen



@Composable
fun MainGraph(
    appState: AppState,
    bottomBarState : MutableState<Boolean>
) {
    NavHost(
        navController = appState.navController,
        startDestination = FIRST_SCREEN,
        modifier = Modifier
    ){
        composable(FIRST_SCREEN) {
            bottomBarState.value = false
            FirstScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
        }
        composable(EMAIL_LOGIN_SCREEN){
            bottomBarState.value = false
            LoginScreen(
                openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) },
                openScreen = { route -> appState.navigate(route) })

        }
        composable(FACEBOOK_LOGIN_SCREEN){
            bottomBarState.value = false
            FaceBookLoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
        }
        composable(SIGNUP_SCREEN){
            bottomBarState.value = false
            SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
        }
        composable(FEED_SCREEN){
            bottomBarState.value = true
            FeedScreen(
                openScreen = {route -> appState.navigate(route)}
            )
        }
        composable(PROFILE_SCREEN){
            bottomBarState.value = true
            ProfileScreen(
                restartApp = {route -> appState.clearAndNavigate(route)},
//                openScreen = {route -> appState.navigate(route)},
            )
        }

    }
}















