package com.example.androidtemplateproject.screens.detailScreen.domain

data class AppDetails(
    val id: String,
    val name: String,
    val developer: String,
    val category: String,
    val ageRating: Int,
    val size: Float,
    val iconUrl: String,
    val screenshotUrlList: List<String>? = null,
    val description: String,
    val isInWishlist: Boolean = false
)