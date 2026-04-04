package com.example.androidtemplateproject.screens.detailScreen.presentation

import com.example.androidtemplateproject.screens.appList.domain.ApplicationData

sealed interface DetailScreenState {
    data class Content(
        val applicationData: ApplicationData
    ) : DetailScreenState
    data object Loading : DetailScreenState
    data object Error : DetailScreenState
}