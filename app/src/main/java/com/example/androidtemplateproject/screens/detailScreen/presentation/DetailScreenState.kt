package com.example.androidtemplateproject.screens.detailScreen.presentation

import com.example.androidtemplateproject.screens.mainActivity.data.ApplicationData

sealed interface DetailScreenState {
    data class Content constructor(
        val applicationData: ApplicationData
    ) : DetailScreenState
    data object Loading : DetailScreenState
    data object Error : DetailScreenState
}