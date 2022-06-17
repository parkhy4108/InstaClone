package com.devyoung.instaclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.devyoung.instaclone.navigation.NavGraph
//import com.devyoung.instaclone.navigation.navgraph.RootNavGraph
import com.devyoung.base.NavItems
import com.devyoung.instaclone.presentation.Home
import com.devyoung.instaclone.presentation.bottomNavBar.SettingBottomNavigationBar
import com.devyoung.instaclone.ui.theme.InstaCloneTheme
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            InstaCloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val session = false // example 입니다.
                    navController = rememberNavController()

                    if (session) Home()
                    else NavGraph(
                        navController = navController ,
                        startDestination = NavItems.Login.route
                    )
                }
            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    InstaCloneTheme {
//
//    }
//}