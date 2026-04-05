package com.example.androidtemplateproject.screens.appList.domain

import javax.inject.Inject

class GetApplicationsUseCase @Inject constructor(
    private val repository: AppRepository
) {
    // FIXME: - ЮЗКЕЙС для примера. Помним что проксирующий юзкейс == бесполезный юзкейс
    operator suspend fun invoke(): List<ApplicationData> {
        return repository.getApplications()
    }
}