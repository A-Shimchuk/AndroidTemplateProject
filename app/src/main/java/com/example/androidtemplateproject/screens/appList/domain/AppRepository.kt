package com.example.androidtemplateproject.screens.appList.domain

import com.example.androidtemplateproject.screens.appList.data.ApplicationData

interface AppRepository {
    suspend fun getApplications(): List<ApplicationData>
    suspend fun getApplicationById(id: String): ApplicationData?
}