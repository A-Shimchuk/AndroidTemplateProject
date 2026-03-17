package com.example.androidtemplateproject.models
import androidx.compose.ui.graphics.vector.ImageVector

data class AppicationData(
    val id: String,
    val icon: ImageVector,
    val title: String,
    val subtitle: String,
    val category: String
)