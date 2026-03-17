package com.example.androidtemplateproject.screens.mainActivity
import com.example.androidtemplateproject.dto.ApplicationData

data class MainActivityState(
    val isLoading: Boolean = false,
    val items: List<ApplicationData> = emptyList()
)