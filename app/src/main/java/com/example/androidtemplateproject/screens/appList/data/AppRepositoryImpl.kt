package com.example.androidtemplateproject.screens.appList.data

import com.example.androidtemplateproject.screens.appList.domain.AppRepository
import com.example.androidtemplateproject.screens.appList.domain.ApplicationData
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val mapper: ApplicationMapper,
    private val apiService: ApplicationsApiService
): AppRepository {
    override suspend fun getApplications(): List<ApplicationData> {
        return apiService.getApplications().map { mapper.toApplicationData(it) }
    }
}