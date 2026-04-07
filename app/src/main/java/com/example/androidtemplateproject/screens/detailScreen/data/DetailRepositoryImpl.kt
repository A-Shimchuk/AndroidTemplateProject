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
        // Try to get from database first
        val entity = dao.getAppDetails(id).first()

        return if (entity != null) {
            // Map entity to domain model
            entityMapper.toDomain(entity)
        } else {
            // If not in database, fetch from network
            val dto = apiService.getApplicationDetails(id)
            val appDetails = networkMapper.toAppDetails(dto)

            // Save to database on IO dispatcher
            withContext(Dispatchers.IO) {
                val entityToSave = entityMapper.toEntity(appDetails)
                dao.insertAppDetails(entityToSave)
            }

            appDetails
        }
    }
}