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
import com.example.androidtemplateproject.screens.mainActivity.data.ApplicationData
import com.example.androidtemplateproject.screens.mainActivity.presentation.MainActivityViewModel
import com.example.androidtemplateproject.screens.mainActivity.presentation.MainActivityEvent
import com.example.androidtemplateproject.screens.mainActivity.presentation.uiComponents.applicationList.ApplicationsListView
import com.example.androidtemplateproject.screens.mainActivity.presentation.uiComponents.header.HeaderView
import com.example.androidtemplateproject.screens.mainActivity.presentation.theme.MainColor


@Composable
fun MainActivityScreen(
    onAppClicked: (String) -> Unit
) {
    // Благодаря viewModel - модель не пересоздается при пересоздании composable-функции
    val viewModel = viewModel<MainActivityViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    // Управление Snackbar
    val snackbarHostState = remember { SnackbarHostState() }

    // Подписываемся на события snackbar из модельки
    LaunchedEffect(Unit) {
        viewModel.snackbarEvent.collect { event ->
            when (event) {
                is MainActivityEvent.SnackbarShown -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    when (val currentState = state) {
        is MainActivityState.Content -> {
            ApplicationsList(
                onAppClicked = onAppClicked,
                applications = currentState.applicationsData,
                viewModel = viewModel,
                snackbarHostState = snackbarHostState
            )
        }

        MainActivityState.Error -> {
            Error()
        }

        MainActivityState.Loading -> {
            Loader()
        }
    }
}

@Composable
private fun ApplicationsList(
    onAppClicked: (String) -> Unit,
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
                    viewModel.onIconClick()
                },
                onCardClick = onAppClicked
            )
        }

        SnackbarHost(hostState = snackbarHostState)
    }
}

@Composable
private fun Loader() {
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
private fun Error() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor),
        contentAlignment = Alignment.Center
    ) {
        Text("Что-то пошло не так")
    }
}