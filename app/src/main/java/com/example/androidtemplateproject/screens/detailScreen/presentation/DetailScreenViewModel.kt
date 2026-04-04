package com.example.androidtemplateproject.screens.detailScreen.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtemplateproject.dto.ApplicationData
import com.example.androidtemplateproject.screens.appList.presentation.Screen
import com.example.androidtemplateproject.screens.repositories.AppRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailScreenViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
    private val appRepository = AppRepository()
    private val _state = MutableStateFlow<DetailScreenState>(DetailScreenState.Loading)
    val state: StateFlow<DetailScreenState> = _state.asStateFlow()

    init {
        val id: String = checkNotNull(savedStateHandle[Screen.Detail.ARG_APPLICATION_ID])
        getAppById(id)
    }

    private fun getAppById(id: String) {
        viewModelScope.launch {
            _state.value = DetailScreenState.Loading
            delay(timeMillis = DELAY_VALUE)

            val application: ApplicationData? = appRepository.getApplicationById(id)
            application?.let {
                _state.value = DetailScreenState.Content(application)
            } ?: run {
                _state.value = DetailScreenState.Error
            }
        }
    }

    companion object LocalConstants {
        private const val DELAY_VALUE: Long = 800
    }
}