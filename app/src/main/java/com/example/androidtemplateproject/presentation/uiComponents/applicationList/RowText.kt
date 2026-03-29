package com.example.androidtemplateproject.presentation.uiComponents.applicationList

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
internal fun RowText(text: String, style: TextStyle) {
    Text(
        text = text,
        style = style,
        modifier = Modifier.fillMaxWidth()
    )
}