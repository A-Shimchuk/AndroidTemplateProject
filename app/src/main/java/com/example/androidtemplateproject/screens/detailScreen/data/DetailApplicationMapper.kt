package com.example.androidtemplateproject.screens.detailScreen.data

import com.example.androidtemplateproject.screens.detailScreen.domain.AppDetails
import com.example.androidtemplateproject.screens.detailScreen.data.network.NetworkApplicationDetailDTO

class DetailApplicationMapper {
    fun toAppDetails(dto: NetworkApplicationDetailDTO): AppDetails {
        return AppDetails(
            id = dto.id,
            name = dto.name,
            developer = "Unknown",
            category = dto.category,
            ageRating = 0,
            size = 0f,
            iconUrl = dto.iconUrl,
            screenshotUrlList = null,
            description = dto.description,
            isInWishlist = false
        )
    }
}