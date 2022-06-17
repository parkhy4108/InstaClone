package com.devyoung.instaclone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devyoung.base.NavItems
import com.devyoung.feeds.FeedsScreen
import com.devyoung.profile.ProfileScreen

@Composable
fun HomeNav(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavItems.Feeds.route)
    {
        composable(NavItems.Feeds.route){
            FeedsScreen()
        }
        composable(NavItems.Profile.route){
            ProfileScreen()
        }
    }
}