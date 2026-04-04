package com.example.androidtemplateproject.dto
import androidx.compose.ui.graphics.vector.ImageVector

data class ApplicationData(
    val id: String,
    val icon: ImageVector,
    val title: String,
    val subtitle: String,
    val category: String
)