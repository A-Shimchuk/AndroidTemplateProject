package com.example.androidtemplateproject.screens.appList.domain

class GetApplicationsUseCase(
    private val repository: AppRepository
) {
    // FIXME: - ЮЗКЕЙС для примера. Помним что проксирующий юзкейс == бесполезный юзкейс
    operator suspend fun invoke(): List<ApplicationData> {
        // Здесь какая-то логика
        return repository.getApplications()
    }
}