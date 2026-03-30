package com.example.androidtemplateproject.screens.mainActivity.domain

import com.example.androidtemplateproject.screens.mainActivity.domain.AppRepository
import com.example.androidtemplateproject.screens.mainActivity.data.ApplicationData

class GetApplicationsUseCase(
    private val repository: AppRepository
) {
    // FIXME: - ЮЗКЕЙС для примера. Помним что проксирующий юзкейс == бесполезный юзкейс
    operator suspend fun invoke(): List<ApplicationData> {
        // Здесь какая-то логика
        return repository.getApplications()
    }
}