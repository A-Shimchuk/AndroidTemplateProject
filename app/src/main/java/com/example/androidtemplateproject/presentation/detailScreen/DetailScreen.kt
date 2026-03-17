package com.example.androidtemplateproject

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidtemplateproject.models.AppicationData
import com.example.androidtemplateproject.presentation.uiComponents.applicationList.RowText
import com.example.androidtemplateproject.presentation.uiComponents.applicationList.TextSpacer

@Composable
internal fun DetailScreen(data: AppicationData) {
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
            imageVector = data.icon,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null,
            modifier = Modifier.padding(bottom = defaultPadding.dp)
        )

        RowText(
            text = data.title,
            style = MaterialTheme.typography.headlineMedium
        )
        TextSpacer(height = spacerHeight)

        RowText(
            text = data.subtitle,
            style = MaterialTheme.typography.bodyLarge
        )
        TextSpacer(height = spacerHeight)

        RowText(
            text = "${category} ${data.category}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}