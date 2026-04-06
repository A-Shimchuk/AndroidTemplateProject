package com.example.androidtemplateproject.screens.detailScreen.domain

import com.example.androidtemplateproject.screens.appList.domain.ApplicationData

interface DetailRepository {
    suspend fun getApplicationById(id: String): ApplicationData?
}