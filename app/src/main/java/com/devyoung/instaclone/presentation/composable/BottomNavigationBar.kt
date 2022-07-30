package com.devyoung.instaclone.presentation.composable

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState




@Composable
fun BottomNavigationBar(
    modifier: Modifier,
    navController: NavHostController,
){
    val screens = listOf(
        BottomBarScreen.Feed,
        BottomBarScreen.Search,
        BottomBarScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if(bottomBarDestination){
        BottomNavigation(
            modifier= modifier.graphicsLayer {
                shape = RectangleShape
                clip = true
            },
            backgroundColor = Color.White,
            contentColor = Color.Black
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = null,
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = null
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                navController.graph.startDestinationRoute?.let {
                    popUpTo(it) { saveState = true }
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}