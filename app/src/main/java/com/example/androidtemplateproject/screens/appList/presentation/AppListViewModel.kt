package com.example.androidtemplateproject.screens.appList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtemplateproject.screens.appList.data.ApplicationMapper
import com.example.androidtemplateproject.screens.appList.data.ApplicationsAPI
import com.example.androidtemplateproject.screens.appList.domain.GetApplicationsUseCase
import com.example.androidtemplateproject.screens.appList.domain.ApplicationData
import com.example.androidtemplateproject.screens.appList.data.AppRepositoryImpl
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
    // FIXME: Временно инжектим так, далее будет DI
    private val useCase = GetApplicationsUseCase(
        repository = AppRepositoryImpl(
            mapper = ApplicationMapper(),
            api = ApplicationsAPI()
        )
    )

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
            delay(timeMillis = DELAY_VALUE)

            val applications: List<ApplicationData> = useCase()
            _state.value = AppListState.Content(applications)
        }
    }

    private companion object LocalConstants{
        private const val SNACKBAR_TEXT = "Произошел показ снекбара"
        private const val DELAY_VALUE: Long = 300
    }
}