package com.example.androidtemplateproject.screens.mainActivity.data

import com.example.androidtemplateproject.screens.mainActivity.domain.AppRepository
import com.example.androidtemplateproject.screens.mainActivity.data.ApplicationData

class AppRepositoryImpl(
    private val mapper: ApplicationMapper,
    private val api: ApplicationsAPI
): AppRepository {
    override suspend fun getApplications(): List<ApplicationData> {
        return api.getApplications().map { mapper.toApplicationData(it) }
    }

    override suspend fun getApplicationById(id: String): ApplicationData? {
        return api.getApplications().find {
            it.id == id
        }?.let { mapper.toApplicationData(it) } ?: null
    }
}