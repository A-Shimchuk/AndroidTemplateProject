package com.example.androidtemplateproject.screens.detailScreen.presentation

import com.example.androidtemplateproject.screens.detailScreen.domain.AppDetails

sealed interface DetailScreenState {
    data class Content(
        val appDetails: AppDetails
    ) : DetailScreenState
    data object Loading : DetailScreenState
    data object Error : DetailScreenState
}