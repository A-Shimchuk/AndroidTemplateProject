package com.example.androidtemplateproject.screens.appList.data

import com.example.androidtemplateproject.screens.appList.domain.AppRepository
import com.example.androidtemplateproject.screens.appList.domain.ApplicationData
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
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