package com.example.androidtemplateproject.screens.appList.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.androidtemplateproject.DetailScreen
import com.example.androidtemplateproject.screens.repositories.AppRepository
import com.example.androidtemplateproject.screens.appList.theme.AndroidTemplateProjectTheme
import com.example.androidtemplateproject.screens.appList.presentation.AppListScreen

sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object Detail : Screen("detail/{applicationId}") {
        const val ARG_APPLICATION_ID = "applicationId"
        fun createRoute(applicationId: String) = "detail/$applicationId"
    }
}

class AppList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTemplateProjectTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Main.route,
                        modifier = Modifier.padding()
                    ) {
                        composable(Screen.Main.route) {
                            AppListScreen(
                                onAppClicked = { applicationId ->
                                    navController.navigate(Screen.Detail.createRoute(applicationId))
                                }
                            )
                        }

                        composable(
                            route = Screen.Detail.route,
                            arguments = listOf(navArgument(Screen.Detail.ARG_APPLICATION_ID) {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            DetailScreen()
                        }
                    }
                }
            }
        }
    }
}