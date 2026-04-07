package com.example.androidtemplateproject.screens.detailScreen.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetApplicationDetailsUseCase @Inject constructor(
    private val repository: DetailRepository
) {
    suspend operator fun invoke(id: String): AppDetails? {
        return repository.getApplicationById(id)
    }

    fun observeAppDetails(id: String): Flow<AppDetails?> {
        return repository.observeAppDetails(id)
    }

    suspend fun toggleWishlist(id: String) {
        repository.toggleWishlist(id)
    }
}