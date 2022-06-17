package com.devyoung.instaclone.presentation.bottomNavBar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.devyoung.base.NavItems
import com.devyoung.instaclone.R


@Composable
fun BottomNavigationBar(
    modifier: Modifier,
    navController: NavController
){
    val screenItems = listOf(
        NavItems.Feeds,
        NavItems.Profile
    )

    BottomNavigation(
        modifier= modifier.graphicsLayer {
            shape = RoundedCornerShape(20.dp)
            clip = true
        },
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        screenItems.forEach { screen ->
            val iconImage = if (screen == NavItems.Feeds) R.drawable.ic_home else R.drawable.ic_person
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = iconImage),
                        contentDescription = screen.description)
                },
                label = null,
                selected = currentRoute == screen.route,

                onClick = {
                    navController.navigate(screen.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}