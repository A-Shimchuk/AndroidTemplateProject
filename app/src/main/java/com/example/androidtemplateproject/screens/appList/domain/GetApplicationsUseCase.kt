package com.example.androidtemplateproject.screens.appList.domain

import com.example.androidtemplateproject.screens.appList.domain.AppRepository
import com.example.androidtemplateproject.screens.appList.data.ApplicationData

class GetApplicationsUseCase(
    private val repository: AppRepository
) {
    // FIXME: - ЮЗКЕЙС для примера. Помним что проксирующий юзкейс == бесполезный юзкейс
    operator suspend fun invoke(): List<ApplicationData> {
        // Здесь какая-то логика
        return repository.getApplications()
    }
}