package com.example.androidtemplateproject.presentation.uiComponents.applicationList

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextSpacer(height: Int) {
    Spacer(modifier = Modifier.height(height.dp))
}