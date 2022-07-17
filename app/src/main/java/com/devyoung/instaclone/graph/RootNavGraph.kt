package com.devyoung.instaclone.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devyoung.base.FIRST
import com.devyoung.base.HOME
import com.devyoung.base.ROOT
import com.devyoung.login.presentation.screen.first.FirstScreen

@Composable
fun RootNavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        modifier = modifier,
        navController = navController,
        route = ROOT,
        startDestination = FIRST
    ){
        composable(route = FIRST) {
            FirstScreen(
                openAndPopUp = { route ->
                    navController.popBackStack()
                    navController.navigate(route)
                }
            )
        }

        authNavGraph(navController)

        composable(route = HOME){
            HomeScreen()
        }
    }
}
