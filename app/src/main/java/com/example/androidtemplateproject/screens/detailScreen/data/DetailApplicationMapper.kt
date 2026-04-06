package com.example.androidtemplateproject.screens.detailScreen.data

import com.example.androidtemplateproject.screens.appList.domain.ApplicationData
import com.example.androidtemplateproject.screens.detailScreen.data.network.NetworkApplicationDetailDTO
// MARK: - Маппер пока дублирует ApplicationMapper, но подразумевается, что они будут работать с разными моделями
    class DetailApplicationMapper {
    fun toApplicationData(dto: NetworkApplicationDetailDTO): ApplicationData {
        return ApplicationData(
            id = dto.id,
            iconUrl = dto.iconUrl,
            name = dto.name,
            description = dto.description,
            category = dto.category
        )
    }
}