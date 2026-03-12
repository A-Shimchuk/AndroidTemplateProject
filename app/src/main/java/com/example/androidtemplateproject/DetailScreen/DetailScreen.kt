package com.example.androidtemplateproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androidtemplateproject.Models.AppicationData

@Composable
internal fun DetailScreen(data: AppicationData) {
    val safeAreaTopPadding = 60
    val defaultPadding = 16

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
            imageVector = data.icon,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null,
            modifier = Modifier.padding(bottom = defaultPadding.dp)
        )

        TitleText(text = data.title)
        TextSpacer()

        SubtitleText(text = data.subtitle)
        TextSpacer()

        CategoryText(text = data.category)
    }
}

@Composable
private fun TitleText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium
    )
}

@Composable
private fun SubtitleText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
private fun CategoryText(text: String) {
    Text(
        text = "Категория: ${text}",
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
private fun TextSpacer() {
    Spacer(modifier = Modifier.height(8.dp))
}
