package com.devyoung.instaclone.presentation

import android.content.res.Resources
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.devyoung.base.*
import com.devyoung.base.snackbar.SnackBarManager
import com.devyoung.feeds.presentation.screen.feed.FeedScreen
import com.devyoung.feeds.presentation.screen.post.PostScreen
import com.devyoung.instaclone.graph.RootNavGraph
//import com.devyoung.instaclone.navigation.MainGraph
import com.devyoung.instaclone.presentation.composable.BottomNavigationBar
import com.devyoung.instaclone.ui.theme.InstaCloneTheme
import com.devyoung.login.presentation.screen.emailLogin.LoginScreen
import com.devyoung.login.presentation.screen.first.FirstScreen
import com.devyoung.login.presentation.screen.signup.SignUpScreen
import com.devyoung.profile.presentation.ProfileScreen
import kotlinx.coroutines.CoroutineScope

@Composable
fun InstaCloneApp() {
    InstaCloneTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {

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
                scaffoldState = scaffoldState,
            ) {
                RootNavGraph(navController = navController, modifier = Modifier.padding(it))
            }
        }
    }
}
//@Composable
//fun rememberAppState(
//    scaffoldState: ScaffoldState = rememberScaffoldState(),
//    navController: NavHostController = rememberNavController(),
//    snackBarManager: SnackBarManager = SnackBarManager,
//    resources: Resources = resources(),
//    coroutineScope: CoroutineScope = rememberCoroutineScope(),
//) = remember(scaffoldState, navController, snackBarManager, resources, coroutineScope)  {
//    AppState(scaffoldState,navController,snackBarManager,resources,coroutineScope)
//}

//@Composable
//@ReadOnlyComposable
//fun resources(): Resources {
//    LocalConfiguration.current
//    return LocalContext.current.resources
//}


//fun NavGraphBuilder.instaCloneGraph(appState: AppState) {
//
//    composable(FIRST_SCREEN) {
//        FirstScreen(openAndPopUp = { route, popUp ->
//            appState.navigateAndPopUp(route, popUp)
//        })
//    }
//
//    composable(LOGIN_SCREEN) {
//        LoginScreen(
//            openAndPopUp = {route , popUp -> appState.navigateAndPopUp(route, popUp)},
//            openScreen = { route -> appState.navigate(route) })
//    }
//
//
//    composable(SIGNUP_SCREEN) {
//        SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
//    }
//
//    composable(FEED_SCREEN) {
//        FeedScreen(
//            openScreen = { route -> appState.navigate(route) }
//        )
//    }
//
//
//    composable(POST_SCREEN) {
//        PostScreen(
//            popUpScreen = { appState.popUp() }
//        )
//    }
//
//
//    composable(PROFILE_SCREEN) {
//        ProfileScreen(
//            restartApp = { route -> appState.clearAndNavigate(route) },
//        )
//    }
//}












