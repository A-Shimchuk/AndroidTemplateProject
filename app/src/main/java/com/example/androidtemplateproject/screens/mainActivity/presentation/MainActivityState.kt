package com.example.androidtemplateproject.screens.mainActivity.presentation

import com.example.androidtemplateproject.screens.mainActivity.data.ApplicationData

sealed interface MainActivityState {
    data class Content(
        val applicationsData: List<ApplicationData>
    ) : MainActivityState
    data object Loading : MainActivityState
    data object Error : MainActivityState
}