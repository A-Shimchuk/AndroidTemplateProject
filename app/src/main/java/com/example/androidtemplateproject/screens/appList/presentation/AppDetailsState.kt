package com.example.androidtemplateproject.screens.appList.presentation

import com.example.androidtemplateproject.screens.appList.data.ApplicationData

sealed interface AppDetailsState {
    data class Content(
        val applicationsData: List<ApplicationData>
    ) : AppDetailsState
    data object Loading : AppDetailsState
    data object Error : AppDetailsState
}