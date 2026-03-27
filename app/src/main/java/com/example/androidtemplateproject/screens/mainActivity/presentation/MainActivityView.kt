package com.example.androidtemplateproject.screens.mainActivity.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.androidtemplateproject.dto.ApplicationData
import com.example.androidtemplateproject.screens.mainActivity.presentation.MainActivityState
import com.example.androidtemplateproject.screens.mainActivity.presentation.MainActivityViewModel
import com.example.androidtemplateproject.screens.mainActivity.presentation.uiComponents.applicationList.ApplicationsListView
import com.example.androidtemplateproject.screens.mainActivity.presentation.uiComponents.header.HeaderView
import com.example.androidtemplateproject.screens.mainActivity.theme.MainColor


@Composable
fun MainActivityView(navController: NavController) {
    // Благодаря viewModel - модель не пересоздается при пересоздании composable-функции
    val viewModel = viewModel<MainActivityViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val currentState = state) {
        is MainActivityState.Content -> {
            showApplicationsList(navController, currentState.applicationsData)
        }
        MainActivityState.Error -> {
            showError()
        }
        MainActivityState.Loading -> {
            showLoader()
        }
    }
}

@Composable
private fun showApplicationsList(navController: NavController, applications: List<ApplicationData>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .background(MainColor)
    ) {
        HeaderView()

        ApplicationsListView(applications = applications) { applicationId ->
            navController.navigate("DetailScreen/$applicationId")
        }
    }
}

@Composable
private fun showLoader() {
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
private fun showError() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor),
        contentAlignment = Alignment.Center
    ) {
        Text("Что-то пошло не так")
    }
}