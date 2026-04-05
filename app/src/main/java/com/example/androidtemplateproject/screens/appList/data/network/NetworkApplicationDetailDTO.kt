package com.example.androidtemplateproject.screens.appList.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkApplicationDetailDTO(
    @Json(name = "id")
    val id: String,

    @Json(name = "iconUrl")
    val iconUrl: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "description")
    val description: String,

    @Json(name = "category")
    val category: String
)