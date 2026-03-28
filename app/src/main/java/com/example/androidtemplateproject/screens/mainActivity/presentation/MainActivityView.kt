package com.example.androidtemplateproject.screens.mainActivity.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.androidtemplateproject.dto.ApplicationData
import com.example.androidtemplateproject.screens.mainActivity.domain.MainActivityViewModel
import com.example.androidtemplateproject.screens.mainActivity.presentation.uiComponents.applicationList.ApplicationsListView
import com.example.androidtemplateproject.screens.mainActivity.presentation.uiComponents.header.HeaderView
import com.example.androidtemplateproject.screens.mainActivity.theme.MainColor


@Composable
fun MainActivityView(navController: NavController) {
    // Благодаря viewModel - модель не пересоздается при пересоздании composable-функции
    val viewModel = viewModel<MainActivityViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    // Управление Snackbar
    val snackbarHostState = remember { SnackbarHostState() }

    // Подписываемся на события snackbar из модельки
    LaunchedEffect(Unit) {
        viewModel.snackbarEvent.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    when (val currentState = state) {
        is MainActivityState.Content -> {
            ShowApplicationsList(
                navController,
                currentState.applicationsData,
                viewModel,
                snackbarHostState
            )
        }

        MainActivityState.Error -> {
            ShowError()
        }

        MainActivityState.Loading -> {
            ShowLoader()
        }
    }
}

@Composable
private fun ShowApplicationsList(
    navController: NavController,
    applications: List<ApplicationData>,
    viewModel: MainActivityViewModel,
    snackbarHostState: SnackbarHostState
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MainColor)
        ) {
            HeaderView()

            ApplicationsListView(
                applications = applications,
                onIconClick = { applicationId ->
                    viewModel.onIconClick(applicationId)
                },
                onCardClick = { applicationId ->
                    navController.navigate("DetailScreen/$applicationId")
                }
            )
        }

        SnackbarHost(hostState = snackbarHostState)
    }
}

@Composable
private fun ShowLoader() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color.White)
    }
}

@Composable
private fun ShowError() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor),
        contentAlignment = Alignment.Center
    ) {
        Text("Что-то пошло не так")
    }
}