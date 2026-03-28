package com.example.androidtemplateproject.screens.detailScreen.domain

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtemplateproject.dto.ApplicationData
import com.example.androidtemplateproject.screens.detailScreen.presentation.DetailScreenState
import com.example.androidtemplateproject.screens.repositories.AppRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailScreenViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
    companion object LocalConstants {
        val delayValue: Long = 800
    }

    private val appRepository = AppRepository()
    private val _state = MutableStateFlow<DetailScreenState>(DetailScreenState.Loading)
    val state: StateFlow<DetailScreenState> = _state.asStateFlow()

    init {
        val id: String = checkNotNull(savedStateHandle["applicationId"])
        getAppById(id)
    }

    private fun getAppById(id: String) {
        viewModelScope.launch {
            _state.value = DetailScreenState.Loading
            delay(timeMillis = delayValue)

            val application: ApplicationData? = appRepository.getApplicationById(id)
            application?.let {
                _state.value = DetailScreenState.Content(application)
            } ?: run {
                _state.value = DetailScreenState.Error
            }
        }
    }
}