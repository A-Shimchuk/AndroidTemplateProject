package com.example.androidtemplateproject.screens.mainActivity.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtemplateproject.dto.ApplicationData
import com.example.androidtemplateproject.screens.detailScreen.presentation.DetailScreenViewModel
import com.example.androidtemplateproject.screens.repositories.AppRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private companion object LocalConstants{
        val snackbarText = "Вы велиполепны"
    }

    private val appRepository = AppRepository()
    private val _state = MutableStateFlow<MainActivityState>(MainActivityState.Loading)
    val state: StateFlow<MainActivityState> = _state.asStateFlow()

    init {
        getApplciations()
    }

    private fun getApplciations() {
        viewModelScope.launch {
            _state.value = MainActivityState.Loading
            delay(timeMillis = DetailScreenViewModel.LocalConstants.delayValue)

            var applications: List<ApplicationData> = appRepository.getApplications()
            applications?.let {
                _state.value = MainActivityState.Content(applications)
            } ?: run {
                _state.value = MainActivityState.Error
            }
        }
    }
}