package com.example.androidtemplateproject.screens.appList.presentation.uiComponents.header

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
internal fun HeaderTitle(text: String, modifier: Modifier) {
    Text(
        text = text,
        color = Color.White,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}