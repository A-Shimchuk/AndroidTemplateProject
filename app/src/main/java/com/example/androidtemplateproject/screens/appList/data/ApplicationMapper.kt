package com.example.androidtemplateproject.screens.appList.data

import com.example.androidtemplateproject.screens.appList.domain.ApplicationData

class ApplicationMapper {
    fun toApplicationData(dto: NetworkApplicationDTO): ApplicationData {
        return ApplicationData(
            id = dto.id,
            iconUrl = dto.iconUrl,
            name = dto.name,
            description = dto.description,
            category = dto.category
        )
    }
}