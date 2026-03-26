package com.example.androidtemplateproject.screens.mainActivity.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.androidtemplateproject.screens.mainActivity.domain.MainActivityViewModel
import com.example.androidtemplateproject.screens.mainActivity.domain.ScreenEvent
import com.example.androidtemplateproject.screens.mainActivity.presentation.uiComponents.applicationList.ApplicationsListView
import com.example.androidtemplateproject.screens.mainActivity.presentation.uiComponents.header.HeaderView
import com.example.androidtemplateproject.screens.mainActivity.theme.MainColor

@Composable
fun MainActivityView(navController: NavController, viewModel: MainActivityViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .background(MainColor)
    ) {
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MainColor),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        } else {
            HeaderView()

            ApplicationsListView(applications = state.items) { applicationId ->
                navController.navigate("DetailScreen/$applicationId")
            }
        }
    }

    LaunchedEffect(viewModel.events) {
        viewModel.events.collect { event ->
            when (event) {
                is ScreenEvent.NetworkError -> {
                    // TODO: Some error
                }

                is ScreenEvent.ShowSnackbar -> {
                    // TODO:
                }

                is ScreenEvent.Loading -> {
                    // TODO:
                }

                is ScreenEvent.Loaded -> {
                    // TODO:
                }
            }
        }
    }
}