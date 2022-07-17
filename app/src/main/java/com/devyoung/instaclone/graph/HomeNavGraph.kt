package com.devyoung.instaclone.graph

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devyoung.base.HOME
import com.devyoung.base.POST_SCREEN
import com.devyoung.feeds.presentation.screen.feed.FeedScreen
import com.devyoung.feeds.presentation.screen.post.PostScreen
import com.devyoung.instaclone.presentation.composable.BottomBarScreen
import com.devyoung.instaclone.presentation.composable.BottomNavigationBar
import com.devyoung.profile.presentation.ProfileScreen


@Composable
fun HomeScreen(){
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = it,
                modifier = Modifier,
                snackbar = { snackBarData ->
                    Snackbar(snackBarData)
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                modifier = Modifier.height(45.dp),
                navController = navController
            )
        },
        scaffoldState = scaffoldState,
    ) {
        HomeNavGraph(navController, modifier = Modifier.padding(it))
    }
}


@Composable
fun HomeNavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        modifier = modifier.padding(),
        navController = navController,
        route = HOME,
        startDestination = BottomBarScreen.Feed.route
    ){
        composable(route = BottomBarScreen.Feed.route){
            FeedScreen(
                openScreen = {navController.navigate(POST_SCREEN) }
            )
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(
                restartApp = {  },
            )
        }
        composable(route = POST_SCREEN) {
            PostScreen(
                popUpScreen = { navController.popBackStack() }
            )
        }
    }
}

