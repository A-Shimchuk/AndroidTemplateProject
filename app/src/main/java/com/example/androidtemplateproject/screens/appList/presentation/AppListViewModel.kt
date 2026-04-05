package com.example.androidtemplateproject.screens.appList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtemplateproject.screens.appList.domain.ApplicationData
import com.example.androidtemplateproject.screens.appList.domain.GetApplicationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface AppListEvent {
    data class SnackbarShown(val message: String) : AppListEvent
}

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val useCase: GetApplicationsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AppListState>(AppListState.Loading)
    val state: StateFlow<AppListState> = _state.asStateFlow()

    private val _snackbarEvent = Channel<AppListEvent>()
    val snackbarEvent = _snackbarEvent.receiveAsFlow()

    init {
        getApplications()
    }

    fun onIconClick() {
        viewModelScope.launch {
            _snackbarEvent.send(AppListEvent.SnackbarShown(SNACKBAR_TEXT))
        }
    }

    private fun getApplications() {
        viewModelScope.launch {
            _state.value = AppListState.Loading
            try {
                val applications: List<ApplicationData> = useCase()
                _state.value = AppListState.Content(applications)
            } catch (e: Exception) {
                _state.value = AppListState.Error
                // Можно отправить событие snackbar с ошибкой
                _snackbarEvent.send(AppListEvent.SnackbarShown("Ошибка загрузки: ${e.message}"))
            }
        }
    }

    private companion object LocalConstants{
        private const val SNACKBAR_TEXT = "Произошел показ снекбара"
    }
}