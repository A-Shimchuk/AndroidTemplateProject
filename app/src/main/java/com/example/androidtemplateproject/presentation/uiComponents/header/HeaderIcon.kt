package com.example.androidtemplateproject.presentation.uiComponents.header

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.androidtemplateproject.R

@Composable
fun HeaderIcon(modifier: Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(
            R.drawable.ic_launcher_foreground
        ),
        contentDescription = null,
        tint = Color.White
    )
}