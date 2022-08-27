package com.devyoung.instaclone.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devyoung.base.*
import com.devyoung.feeds.presentation.screen.feed.FeedScreen
import com.devyoung.feeds.presentation.screen.followerRequest.FollowerRequestScreen
import com.devyoung.feeds.presentation.screen.post.PostScreen
import com.devyoung.feeds.presentation.screen.storyAdd.StoryAddScreen
import com.devyoung.feeds.presentation.screen.userStory.UserStoryScreen
import com.devyoung.login.presentation.screen.emailLogin.LoginScreen
import com.devyoung.login.presentation.screen.first.FirstScreen
import com.devyoung.login.presentation.screen.signup.SignUpScreen
import com.devyoung.profile.presentation.profileEdit.EditScreen
import com.devyoung.profile.presentation.profileHome.ProfileScreen
import com.devyoung.search.presentation.detail.SearchDetailScreen
import com.devyoung.search.presentation.search.SearchScreen
import com.devyoung.search.presentation.user.UserScreen

@Composable
fun InstaCloneApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val appState = rememberAppState()
        Scaffold(
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    BottomNavigationBar(
                        tabs = appState.bottomBarTabs,
                        currentRoute = appState.currentRoute!!,
                        navigateToRoute = appState::navigateToBottomBarRoute,
                        modifier = Modifier
                    )
                }
            },
            snackbarHost = {
                SnackbarHost(
                    hostState = it,
                    modifier = Modifier,
                    snackbar = { snackBarData ->
                        Snackbar(snackBarData)
                    }
                )
            },
            scaffoldState = appState.scaffoldState,
        ) { innerPadding ->
            NavHost(
                navController = appState.navController,
                startDestination = Screen.First.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                instaCloneGraph(appState)
            }
        }
    }
}

fun NavGraphBuilder.instaCloneGraph(appState: AppState) {
    composable(route = Screen.First.route) {
        FirstScreen(
            openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) },
            navigateBottomBar = { route -> appState.startBottomBarDestination(route) }
        )
    }
    composable(route = Screen.Login.route) {
        LoginScreen(
            navigateBottomBar = { route -> appState.startBottomBarDestination(route) },
            openScreen = { route -> appState.navigate(route) }
        )
    }
    composable(route = Screen.SignUp.route) {
        SignUpScreen(
            navigateBottomBar = { route -> appState.startBottomBarDestination(route) },
        )
    }
    composable(
        route = BottomBarScreen.Feed.route,
    ) {
        FeedScreen(
            openScreen = { route -> appState.navigate(route) }
        )
    }
    composable(
        route = BottomBarScreen.Profile.route,
    ) {
        ProfileScreen(
            openScreen = { route -> appState.navigate(route) },
            restartApp = { route -> appState.clearAndNavigate(route) },
        )
    }
    composable(
        route = Screen.EditProfile.route,
    ) {
        EditScreen(
            popUpScreen = { appState.popUp() }
        )
    }
    composable(route = BottomBarScreen.Search.route) {
        SearchScreen(
            openScreen = { route -> appState.navigate(route) }
        )
    }
    composable(route = Screen.Post.route) {
        PostScreen(
            popUpScreen = { appState.popUp() }
        )
    }
    composable(route = Screen.FollowerRequest.route) {
        FollowerRequestScreen(
            popUpScreen = { appState.popUp() }
        )
    }
    composable(route = Screen.SearchDetail.route) {
        SearchDetailScreen(
            openScreen = { route -> appState.navigate(route) },
            popUpScreen = { appState.popUp() }
        )
    }
    composable(
        route = Screen.User.route,
        arguments = listOf(
            navArgument(ARG_KEY) {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    ) {
        val id = it.arguments?.getString(ARG_KEY)
        UserScreen(
            id = id,
            popUpScreen = { appState.popUp() },
            openScreen = { route -> appState.navigate(route) }
        )
    }
    composable(
        route = Screen.UserStory.route,
        arguments = listOf(
            navArgument(ARG_NICKNAME) {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument(ARG_USERIMG) {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument(ARG_STORYIMG) {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    ) {
        val userNickName = it.arguments?.getString(ARG_NICKNAME)
        val userImage = it.arguments?.getString(ARG_USERIMG)
        val storyImage = it.arguments?.getString(ARG_STORYIMG)
        UserStoryScreen(
            userNickName = userNickName,
            userImage = userImage,
            storyImage = storyImage,
            popUpScreen = { appState.popUp() }
        )
    }
    composable(
        route = Screen.StoryAdd.route
    ) {
        StoryAddScreen(popUpScreen = { appState.popUp() })
    }
}

@Composable
fun BottomNavigationBar(
    tabs: List<BottomBarScreen>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit,
    modifier: Modifier
) {
    val currentSection = tabs.first { it.route == currentRoute }

    BottomNavigation(
        modifier = modifier
            .height(45.dp)
            .graphicsLayer {
                shape = RectangleShape
                clip = true
            },
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        tabs.forEach { section ->
            val selected = section == currentSection
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = section.icon,
                        contentDescription = null
                    )
                },
                label = null,
                selected = selected,
                unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
                onClick = { navigateToRoute(section.route) },
            )
        }
    }
}
