package com.example.androidtemplateproject.screens.mainActivity

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
import com.example.androidtemplateproject.screens.mainActivity.repositories.AppRepository
import com.example.androidtemplateproject.screens.mainActivity.theme.AndroidTemplateProjectTheme

// FIXME: - TO BE DONE
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel = MainActivityViewModel()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTemplateProjectTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "MainActivity",
                        modifier = Modifier.padding()
                    ) {
                        composable("MainActivity") {
                            MainActivityView(navController, viewModel = viewModel)
                        }

                        composable(
                            route = "DetailScreen/{applicationId}",
                            arguments = listOf(navArgument("applicationId") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            val appId = backStackEntry.arguments?.getString("applicationId")
                                ?: return@composable
                            val application =
                                AppRepository().getApplicationById(
                                    appId
                                )
                            application?.let { DetailScreen(data = it) }
                        }
                    }
                }
            }
        }
    }
}