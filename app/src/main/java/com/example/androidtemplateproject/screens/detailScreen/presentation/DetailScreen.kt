package com.example.androidtemplateproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidtemplateproject.dto.ApplicationData
import com.example.androidtemplateproject.baseUIComponents.RowText
import com.example.androidtemplateproject.baseUIComponents.TextSpacer
import com.example.androidtemplateproject.screens.detailScreen.presentation.DetailScreenState
import com.example.androidtemplateproject.screens.detailScreen.presentation.DetailScreenViewModel
import com.example.androidtemplateproject.screens.mainActivity.theme.MainColor

@Composable
internal fun DetailScreen() {
    val viewModel: DetailScreenViewModel = viewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val currentState = state) {
        is DetailScreenState.Content -> {
            appDetailsContent(currentState.applicationData)
        }
        DetailScreenState.Error -> {
            showError()
        }
        DetailScreenState.Loading -> {
            showLoader()
        }
    }
}

@Composable
private fun appDetailsContent(appDetails: ApplicationData) {
    val safeAreaTopPadding = 60
    val defaultPadding = 16
    val spacerHeight = 8
    val category = "Категория:"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = safeAreaTopPadding.dp,
                start = defaultPadding.dp,
                end = defaultPadding.dp
            )
    ) {
        Icon(
            imageVector = appDetails.icon,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null,
            modifier = Modifier.padding(bottom = defaultPadding.dp)
        )

        RowText(
            text = appDetails.title,
            style = MaterialTheme.typography.headlineMedium
        )
        TextSpacer(height = spacerHeight)

        RowText(
            text = appDetails.subtitle,
            style = MaterialTheme.typography.bodyLarge
        )
        TextSpacer(height = spacerHeight)

        RowText(
            text = "${category} ${appDetails.category}",
            style = MaterialTheme.typography.bodyMedium
        )
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