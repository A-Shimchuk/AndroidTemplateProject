package com.example.androidtemplateproject.screens.appList.data

import com.example.androidtemplateproject.sharedDomainComponents.ApplicationDTO
import com.example.androidtemplateproject.screens.appList.data.ApplicationData

class ApplicationMapper {
    fun toApplicationData(dto: ApplicationDTO): ApplicationData {
        return ApplicationData(
            id = dto.id,
            icon = dto.image,
            title = dto.title,
            subtitle = dto.subtitle,
            category = dto.category
        )
    }
}