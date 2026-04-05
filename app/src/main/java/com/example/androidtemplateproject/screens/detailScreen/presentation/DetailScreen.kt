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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidtemplateproject.screens.appList.domain.ApplicationData
import com.example.androidtemplateproject.sharedUIComponents.RowText
import com.example.androidtemplateproject.sharedUIComponents.TextSpacer
import com.example.androidtemplateproject.screens.detailScreen.presentation.DetailScreenState
import com.example.androidtemplateproject.screens.detailScreen.presentation.DetailScreenViewModel
import com.example.androidtemplateproject.sharedUIComponents.theme.MainColor

@Composable
internal fun DetailScreen() {
    val viewModel: DetailScreenViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val currentState = state) {
        is DetailScreenState.Content -> {
            AppDetailsContent(currentState.applicationData)
        }
        DetailScreenState.Error -> {
            Error()
        }
        DetailScreenState.Loading -> {
            Loader()
        }
    }
}

@Composable
private fun AppDetailsContent(appDetails: ApplicationData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 60.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) {
        Icon(
            imageVector = appDetails.icon,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        RowText(
            text = appDetails.title,
            style = MaterialTheme.typography.headlineMedium
        )
        TextSpacer(height = 8)

        RowText(
            text = appDetails.subtitle,
            style = MaterialTheme.typography.bodyLarge
        )
        TextSpacer(height = 8)

        RowText(
            text = "Категория: ${appDetails.category}",
            style = MaterialTheme.typography.bodyMedium
        )
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