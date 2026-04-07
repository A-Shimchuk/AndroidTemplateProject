package com.example.androidtemplateproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.androidtemplateproject.screens.detailScreen.domain.AppDetails
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
            AppDetailsContent(currentState.appDetails, viewModel::toggleWishlist)
        }
        DetailScreenState.Error -> {
            Error()
        }
        DetailScreenState.Loading -> {
            Loader()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppDetailsContent(appDetails: AppDetails, onWishlistClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(appDetails.name) },
                actions = {
                    IconButton(onClick = onWishlistClick) {
                        Icon(
                            imageVector = if (appDetails.isInWishlist)
                                Icons.Filled.Favorite
                            else
                                Icons.Outlined.FavoriteBorder,
                            contentDescription = "Wishlist",
                            tint = if (appDetails.isInWishlist) Color.Red else Color.Gray
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding() + 16.dp,
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            AsyncImage(
                model = appDetails.iconUrl,
                contentDescription = null,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            RowText(
                text = appDetails.name,
                style = MaterialTheme.typography.headlineMedium
            )
            TextSpacer(height = 8)

            RowText(
                text = appDetails.description,
                style = MaterialTheme.typography.bodyLarge
            )
            TextSpacer(height = 8)

            RowText(
                text = "Категория: ${appDetails.category}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
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