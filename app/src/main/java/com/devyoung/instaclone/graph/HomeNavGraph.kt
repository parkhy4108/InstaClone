package com.devyoung.instaclone.graph

import android.content.res.Resources
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devyoung.base.*
import com.devyoung.feeds.presentation.screen.feed.FeedScreen
import com.devyoung.feeds.presentation.screen.followerRequest.FollowerRequestScreen
import com.devyoung.feeds.presentation.screen.post.PostScreen
import com.devyoung.instaclone.presentation.AppState
import com.devyoung.instaclone.presentation.composable.BottomBarScreen
import com.devyoung.instaclone.presentation.composable.BottomNavigationBar
import com.devyoung.instaclone.presentation.rememberAppState
import com.devyoung.profile.presentation.ProfileScreen
import com.devyoung.search.presentation.detail.SearchDetailScreen
import com.devyoung.search.presentation.user.UserScreen
import com.devyoung.search.presentation.search.SearchScreen
import kotlinx.coroutines.CoroutineScope


@Composable
fun HomeScreen(){

    val appState = rememberAppState()

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = it,
                modifier = Modifier.padding(8.dp),
                snackbar = { snackbarData ->
                    Snackbar(snackbarData, contentColor = MaterialTheme.colors.onPrimary)
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                modifier = Modifier.height(45.dp),
                navController = appState.navController
            )
        },
        scaffoldState = appState.scaffoldState,
    ) {
        HomeNavGraph(appState, modifier = Modifier.padding(it))
    }
}

@Composable
fun HomeNavGraph(appState: AppState, modifier: Modifier) {
    NavHost(
        modifier = modifier.padding(),
        navController = appState.navController,
        route = Screen.Home.route,
        startDestination = BottomBarScreen.Feed.route
    ){
        composable(route = BottomBarScreen.Feed.route){
            FeedScreen(
                openScreen = { route -> appState.navigate(route) }
            )
        }
        composable(
            route = BottomBarScreen.Profile.route,
        ) {
            ProfileScreen(
                restartApp = {  },
            )
        }
        composable(route = BottomBarScreen.Search.route){
            SearchScreen(
                openScreen = { route -> appState.navigate(route) }
            )
        }
        composable(route = Screen.Post.route) {
            PostScreen(
                popUpScreen = { appState.popUp() }
            )
        }
        composable(route = Screen.FollowerRequest.route){
            FollowerRequestScreen(
                popUpScreen = { appState.popUp() }
            )
        }
        composable(route = Screen.SearchDetail.route){
            SearchDetailScreen(
                openScreen = { route -> appState.navigate(route) },
                popUpScreen = { appState.popUp() }
            )
        }
        composable(
            route = Screen.User.route,
            arguments = listOf(navArgument(ARG_KEY){
                type = NavType.StringType
            })
        ){
            val id = it.arguments?.getString(ARG_KEY)
            UserScreen(
                id = id,
                popUpScreen = { appState.popUp() }
            )
        }
    }
}



