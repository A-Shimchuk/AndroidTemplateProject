package com.example.androidtemplateproject.screens.appList.presentation

import com.example.androidtemplateproject.screens.appList.domain.ApplicationData

sealed interface AppListState {
    data class Content(
        val applicationsData: List<ApplicationData>
    ) : AppListState
    data object Loading : AppListState
    data object Error : AppListState
}