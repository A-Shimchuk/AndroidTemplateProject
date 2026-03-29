package com.example.androidtemplateproject.screens.mainActivity.domain

import com.example.androidtemplateproject.screens.mainActivity.data.ApplicationData

interface AppRepository {
    suspend fun getApplications(): List<ApplicationData>
    suspend fun getApplicationById(id: String): ApplicationData?
}