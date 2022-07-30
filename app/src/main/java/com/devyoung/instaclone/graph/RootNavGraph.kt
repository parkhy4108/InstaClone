package com.devyoung.instaclone.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devyoung.base.Screen
import com.devyoung.instaclone.presentation.AppState
import com.devyoung.login.presentation.screen.first.FirstScreen

@Composable
fun RootNavGraph(appState: AppState, modifier: Modifier) {
    NavHost(
        modifier = modifier,
        navController = appState.navController,
        route = Screen.Root.route,
        startDestination = Screen.First.route
    ){
        composable(route = Screen.First.route) {
            FirstScreen( openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
        }
        authNavGraph(appState)
        composable(route = Screen.Home.route){
            HomeScreen()
        }
    }
}
