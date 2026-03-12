package com.example.androidtemplateproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import com.example.androidtemplateproject.Models.AppicationData
import com.example.androidtemplateproject.Repository.Repository
import com.example.androidtemplateproject.ui.theme.AndroidTemplateProjectTheme

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
                        startDestination = "MainActivity",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("MainActivity") {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding()
                            ) {
                                HeaderView()

                                ApplicationListView { applicationId ->
                                    navController.navigate("DetailScreen/$applicationId")
                                }
                            }
                        }

                        composable(
                            route = "DetailScreen/{applicationId}",
                            arguments = listOf(navArgument("applicationId") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val appId = backStackEntry.arguments?.getString("applicationId") ?: return@composable
                            val application = Repository.getApplicationById(appId)
                            application?.let {
                                DetailScreen(data = it)
                            }
                        }

                    }

                }
            }
        }
    }

    @Composable
    private fun HeaderView() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Cyan),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(50.dp, 50.dp),
                painter = painterResource(
                    R.drawable.ic_launcher_foreground
                ),
                contentDescription = null
            )

            Text(
                text = "RuStore", // TODO - Шрифт
//                                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            )

            Icon(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(50.dp, 50.dp),
                painter = painterResource(
                    R.drawable.ic_launcher_foreground
                ),
                contentDescription = null
            )
        }
    }

    @Composable
    private fun ApplicationListView(onApplicationClick: (String) -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding()
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    Repository.getApplications().forEach {
                        ApplicationCard(data = it, onApplicationClick = onApplicationClick)
                    }
                }
            }
        }
    }

    @Composable
    private fun ApplicationCard(data: AppicationData, onApplicationClick: (String) -> Unit) {
        Row(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .height(80.dp)
                .fillMaxWidth()
                .clickable {
                    onApplicationClick(data.id)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(80.dp, 80.dp),
                imageVector = data.icon,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(6.dp))

            Column(
                modifier = Modifier
                    .padding(start = 6.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = data.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = data.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = data.category,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}