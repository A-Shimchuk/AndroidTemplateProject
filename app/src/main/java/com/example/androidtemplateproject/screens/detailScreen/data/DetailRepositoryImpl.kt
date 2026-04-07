package com.example.androidtemplateproject.screens.detailScreen.data

import com.example.androidtemplateproject.screens.detailScreen.data.local.AppDetailsDao
import com.example.androidtemplateproject.screens.detailScreen.data.local.AppDetailsEntityMapper
import com.example.androidtemplateproject.screens.detailScreen.data.network.DetailApplicationsApiService
import com.example.androidtemplateproject.screens.detailScreen.domain.AppDetails
import com.example.androidtemplateproject.screens.detailScreen.domain.DetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val dao: AppDetailsDao,
    private val entityMapper: AppDetailsEntityMapper,
    private val networkMapper: DetailApplicationMapper,
    private val apiService: DetailApplicationsApiService
): DetailRepository {
    override suspend fun getApplicationById(id: String): AppDetails? {
        val entity = dao.getAppDetails(id).first()

        return if (entity != null) {
            entityMapper.toDomain(entity)
        } else {
            val dto = apiService.getApplicationDetails(id)
            val appDetails = networkMapper.toAppDetails(dto)

            withContext(Dispatchers.IO) {
                val entityToSave = entityMapper.toEntity(appDetails)
                dao.insertAppDetails(entityToSave)
            }

            appDetails
        }
    }
}