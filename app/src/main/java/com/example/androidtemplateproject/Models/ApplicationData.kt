package com.example.androidtemplateproject.Models
import androidx.compose.ui.graphics.vector.ImageVector

internal data class AppicationData(
    val id: String,
    val icon: ImageVector,
    val title: String,
    val subtitle: String,
    val category: String
)