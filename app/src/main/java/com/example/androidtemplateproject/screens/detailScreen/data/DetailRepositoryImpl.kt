package com.example.androidtemplateproject.screens.detailScreen.data

import com.example.androidtemplateproject.screens.appList.domain.ApplicationData
import com.example.androidtemplateproject.screens.detailScreen.data.network.DetailApplicationsApiService
import com.example.androidtemplateproject.screens.detailScreen.domain.DetailRepository
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val mapper: DetailApplicationMapper,
    private val apiService: DetailApplicationsApiService
): DetailRepository {
    override suspend fun getApplicationById(id: String): ApplicationData? {
        return apiService.getApplicationDetails(id)?.let { mapper.toApplicationData(it) }
    }
}