package com.example.androidtemplateproject.screens.detailScreen.domain

import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun getApplicationById(id: String): AppDetails?
    fun observeAppDetails(id: String): Flow<AppDetails?>
    suspend fun toggleWishlist(id: String)
}