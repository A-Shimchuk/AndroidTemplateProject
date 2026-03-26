package com.example.androidtemplateproject.screens.mainActivity.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtemplateproject.screens.mainActivity.repositories.AppRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed interface ScreenEvent {
    data object NetworkError : ScreenEvent
    data class ShowSnackbar(val message: String) : ScreenEvent
    data object Loading : ScreenEvent
    data object Loaded : ScreenEvent
}

class MainActivityViewModel : ViewModel() {
    private companion object LocalConstants{
        val snackbarText = "Вы велиполепны"
        val loaderDelay: Long = 800
    }

    private val repository = AppRepository()
    private val _state = MutableStateFlow(MainActivityState())
    private val _events = Channel<ScreenEvent>(
        Channel.BUFFERED
    )

    val state: StateFlow<MainActivityState> = _state.asStateFlow()
    val events = _events.receiveAsFlow()

    init {
        makeAppRequest()
    }

    fun makeAppRequest() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true,
            )
            // Иллюзия деятельности
            delay(loaderDelay)

            _state.value = _state.value.copy(
                items = repository.getApplications(),
                isLoading = false
            )

            _events.send(ScreenEvent.ShowSnackbar(snackbarText))
        }
    }
}