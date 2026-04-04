package com.example.androidtemplateproject.screens.appList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtemplateproject.dto.ApplicationData
import com.example.androidtemplateproject.screens.repositories.AppRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed interface AppListEvent {
    data class SnackbarShown(val message: String) : AppListEvent
}

class AppListViewModel : ViewModel() {
    private val appRepository = AppRepository()
    private val _state = MutableStateFlow<AppListState>(AppListState.Loading)
    val state: StateFlow<AppListState> = _state.asStateFlow()

    private val _snackbarEvent = Channel<AppListEvent>()
    val snackbarEvent = _snackbarEvent.receiveAsFlow()

    private companion object LocalConstants{
        private const val SNACKBAR_TEXT = "Произошел показ снекбара"
        private const val DELAY_VALUE: Long = 800
    }

    init {
        getApplciations()
    }

    fun onIconClick() {
        viewModelScope.launch {
            _snackbarEvent.send(AppListEvent.SnackbarShown(SNACKBAR_TEXT))
        }
    }

    private fun getApplciations() {
        viewModelScope.launch {
            _state.value = AppListState.Loading
            delay(timeMillis = DELAY_VALUE)

            val applications: List<ApplicationData> = appRepository.getApplications()
            _state.value = AppListState.Content(applications)
        }
    }
}