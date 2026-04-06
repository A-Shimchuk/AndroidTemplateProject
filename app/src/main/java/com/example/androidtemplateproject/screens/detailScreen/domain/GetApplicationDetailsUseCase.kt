package com.example.androidtemplateproject.screens.detailScreen.domain

import com.example.androidtemplateproject.screens.appList.domain.ApplicationData
import javax.inject.Inject

class GetApplicationDetailsUseCase @Inject constructor(
    private val repository: DetailRepository
) {
    // FIXME: - ЮЗКЕЙС для примера. Помним что проксирующий юзкейс == бесполезный юзкейс
    operator suspend fun invoke(id: String): ApplicationData? {
        // Здесь какая-то логика
        return repository.getApplicationById(id)
    }
}