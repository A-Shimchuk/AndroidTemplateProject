package com.example.androidtemplateproject.screens.detailScreen.domain

import javax.inject.Inject

class GetApplicationDetailsUseCase @Inject constructor(
    private val repository: DetailRepository
) {
    // FIXME: - ЮЗКЕЙС для примера. Помним что проксирующий юзкейс == бесполезный юзкейс
    operator suspend fun invoke(id: String): AppDetails? {
        // Здесь какая-то логика
        return repository.getApplicationById(id)
    }
}