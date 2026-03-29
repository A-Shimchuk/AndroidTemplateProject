package com.example.androidtemplateproject.screens.mainActivity.presentation

import com.example.androidtemplateproject.dto.ApplicationData

sealed interface MainActivityState {
    data class Content constructor(
        val applicationsData: List<ApplicationData>
    ) : MainActivityState
    data object Loading : MainActivityState
    data object Error : MainActivityState
}