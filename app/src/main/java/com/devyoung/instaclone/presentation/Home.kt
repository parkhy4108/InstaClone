package com.devyoung.instaclone.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.devyoung.instaclone.presentation.bottomNavBar.SettingBottomNavigationBar

@Composable
fun Home() {
    val navController = rememberNavController()
    SettingBottomNavigationBar(navController = navController)
}










