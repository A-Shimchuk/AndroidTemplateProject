package com.example.androidtemplateproject.screens.detailScreen.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface DetailApplicationsApiService {

    @GET("catalog/{id}")
    suspend fun getApplicationDetails(@Path("id") id: String): NetworkApplicationDetailDTO

    companion object {
        const val BASE_URL = "http://185.103.109.134/"
    }
}