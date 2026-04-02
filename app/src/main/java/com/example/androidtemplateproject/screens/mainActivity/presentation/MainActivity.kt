package com.example.androidtemplateproject.screens.mainActivity.presentation

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
import com.example.androidtemplateproject.screens.mainActivity.presentation.theme.AndroidTemplateProjectTheme
// FIXME: - Очередной подлый МР, на будущую реализацию ДЗ
sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object Detail : Screen("detail/{applicationId}") {
        fun createRoute(applicationId: String) = "detail/$applicationId"
    }

    companion object {
        const val ARG_APPLICATION_ID = "applicationId"
    }
}
// FIXME: - Homework 14. Empty commit
class MainActivity : ComponentActivity() {
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
                            MainActivityScreen(
                                onAppClicked = { applicationId ->
                                    navController.navigate(Screen.Detail.createRoute(applicationId))
                                }
                            )
                        }

                        composable(
                            route = Screen.Detail.route,
                            arguments = listOf(navArgument(Screen.ARG_APPLICATION_ID) {
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