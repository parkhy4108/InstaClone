package com.devyoung.instaclone.presentation.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.devyoung.base.FEED_SCREEN
import com.devyoung.base.PROFILE_SCREEN
import com.devyoung.base.R.string as AppText
import com.devyoung.base.R.drawable as AppIcon

@Composable
fun BottomNavigationBar(
    modifier: Modifier,
    navController: NavController,
    bottomBarState : MutableState<Boolean>
){
    val screenItems = listOf(
        FEED_SCREEN,
        PROFILE_SCREEN,
    )

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = {it}),
        exit = slideOutVertically(targetOffsetY = {it})
    ) {
        BottomNavigation(
            modifier= modifier.graphicsLayer {
                shape = RectangleShape
                clip = true
            },
            backgroundColor = Color.White,
            contentColor = Color.Black
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            screenItems.forEach { screen ->
                val iconImage =
                    if (screen == FEED_SCREEN) AppIcon.ic_home
                    else AppIcon.ic_person
                val contentDescription =
                    if (screen == FEED_SCREEN) stringResource(id = AppText.feed)
                    else stringResource(id = AppText.profileScreen)
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = iconImage),
                            contentDescription = contentDescription) },
                    label = null,
                    selected = currentRoute == screen,
                    onClick = {
                        navController.navigate(screen) {
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

}