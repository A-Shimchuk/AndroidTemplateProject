package com.example.androidtemplateproject.screens.detailScreen.domain

interface DetailRepository {
    suspend fun getApplicationById(id: String): AppDetails?
}