package com.example.androidtemplateproject.screens.detailScreen.domain

import com.example.androidtemplateproject.screens.appList.domain.AppRepository
import com.example.androidtemplateproject.screens.appList.domain.ApplicationData

class GetApplicationDetailsUseCase(
    private val repository: AppRepository
) {
    // FIXME: - ЮЗКЕЙС для примера. Помним что проксирующий юзкейс == бесполезный юзкейс
    operator suspend fun invoke(id: String): ApplicationData? {
        // Здесь какая-то логика
        return repository.getApplicationById(id)
    }
}