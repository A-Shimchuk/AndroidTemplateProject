package com.example.androidtemplateproject.screens.appList.data

import retrofit2.http.GET

interface ApplicationsApiService {

    @GET("catalog")
    suspend fun getApplications(): List<NetworkApplicationDTO>

    companion object {
        const val BASE_URL = "http://185.103.109.134/"
    }
}
