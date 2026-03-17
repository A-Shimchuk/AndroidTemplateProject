package com.example.androidtemplateproject.presentation.uiComponents.mainActivityView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.androidtemplateproject.presentation.uiComponents.applicationList.ApplicationsListView
import com.example.androidtemplateproject.presentation.uiComponents.header.HeaderView
import com.example.androidtemplateproject.presentation.theme.MainColor

@Composable
fun MainActivityView(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .background(MainColor)
    ) {
        HeaderView()

        ApplicationsListView { applicationId ->
            navController.navigate("DetailScreen/$applicationId")
        }
    }
}