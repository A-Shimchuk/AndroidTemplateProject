package com.example.androidtemplateproject.sharedDomainComponents

import androidx.compose.ui.graphics.vector.ImageVector

data class ApplicationDTO(
    val id: String,
    val image: ImageVector,
    val title: String,
    val subtitle: String,
    val category: String
)