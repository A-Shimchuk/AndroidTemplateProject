package com.example.androidtemplateproject

import android.os.Bundle
import android.util.Size
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.androidtemplateproject.Models.AppicationData
import com.example.androidtemplateproject.Repositories.Repository
import com.example.androidtemplateproject.ui.theme.AndroidTemplateProjectTheme

final class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val mainBackgroundColor = Color(64, 111, 236)

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
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding()
                                    .background(mainBackgroundColor)
                            ) {
                                HeaderView()

                                ApplicationsListView { applicationId ->
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
    private fun HeaderView(modifier: Modifier? = null) {
        val rowHeight = 70
        val defaultPadding = 16
        val safeAreaSpacerHeight = 30
        val iconSize = Size(50, 50)

        Spacer(modifier = Modifier.height(safeAreaSpacerHeight.dp))

        Row(
            modifier = modifier ?: Modifier
                .fillMaxWidth()
                .height(rowHeight.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HeaderIcon(Modifier
                .padding(start = defaultPadding.dp)
                .size(iconSize.width.dp, iconSize.height.dp)
            )

            HeaderTitle("RuStore", modifier = Modifier
                .weight(1f)
                .padding(start = defaultPadding.dp)
            )

            HeaderIcon(Modifier
                .padding(end = defaultPadding.dp)
                .size(iconSize.width.dp, iconSize.height.dp)
            )
        }
    }

    @Composable
    private fun HeaderTitle(text: String, modifier: Modifier) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = modifier
        )
    }

    @Composable
    private fun HeaderIcon(modifier: Modifier) {
        Icon(
            modifier = modifier,
            painter = painterResource(
                R.drawable.ic_launcher_foreground
            ),
            contentDescription = null,
            tint = Color.White
        )
    }

    @Composable
    private fun ApplicationsListView(modifier: Modifier? = null, onApplicationClick: (String) -> Unit) {
        val cornerRadius = 20

        Column(
            modifier = modifier ?: Modifier
                .fillMaxSize()
                .padding()
                .clip(RoundedCornerShape(topStart = cornerRadius.dp, topEnd = cornerRadius.dp))
                .background(Color.White)
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = cornerRadius.dp, topEnd = cornerRadius.dp)
                    )
            ) {
                itemsIndexed(Repository.getApplications()) { index, application ->
                    ApplicationCard(data = application, onApplicationClick = onApplicationClick)

                    if (index < Repository.getApplications().size - 1) {
                        HorizontalDivider(
                            color = Color.LightGray,
                            thickness = 0.5.dp
                        )
                    }
                }

            }
        }
    }

    @Composable
    private fun ApplicationCard(data: AppicationData, onApplicationClick: (String) -> Unit) {
        val appCardImageSize = Size(80, 80)
        val labelsSpacerHeight = 2
        val textColumnStartPadding = 6
        val cornerRadius = 20

        Row(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .height(100.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = cornerRadius.dp, topEnd = cornerRadius.dp))
                .background(Color.White)
                .clickable {
                    onApplicationClick(data.id)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(appCardImageSize.width.dp, appCardImageSize.height.dp),
                imageVector = data.icon,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )

            Column(
                modifier = Modifier
                    .padding(start = textColumnStartPadding.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = data.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(labelsSpacerHeight.dp))

                Text(
                    text = data.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(labelsSpacerHeight.dp))

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