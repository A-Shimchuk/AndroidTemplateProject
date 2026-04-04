package com.example.androidtemplateproject.screens.detailScreen.presentation

import com.example.androidtemplateproject.dto.ApplicationData

sealed interface DetailScreenState {
    data class Content(
        val applicationData: ApplicationData
    ) : DetailScreenState
    data object Loading : DetailScreenState
    data object Error : DetailScreenState
}