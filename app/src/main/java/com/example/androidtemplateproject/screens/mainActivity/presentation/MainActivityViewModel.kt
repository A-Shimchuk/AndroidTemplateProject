package com.example.androidtemplateproject.screens.mainActivity.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtemplateproject.screens.mainActivity.data.ApplicationMapper
import com.example.androidtemplateproject.screens.mainActivity.data.ApplicationsAPI
import com.example.androidtemplateproject.screens.mainActivity.domain.GetApplicationsUseCase
import com.example.androidtemplateproject.screens.mainActivity.data.ApplicationData
import com.example.androidtemplateproject.screens.mainActivity.data.AppRepositoryImpl
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed interface MainActivityEvent {
    data class SnackbarShown(val message: String) : MainActivityEvent
}

class MainActivityViewModel : ViewModel() {
    private companion object LocalConstants{
        private const val SNACKBAR_TEXT = "Произошел показ снекбара"
        private const val DELAY_VALUE: Long = 300
    }

    // FIXME: Временно инжектим так, далее будет DI
    private val useCase = GetApplicationsUseCase(
        repository = AppRepositoryImpl(
            mapper = ApplicationMapper(),
            api = ApplicationsAPI()
        )
    )

    private val _state = MutableStateFlow<MainActivityState>(MainActivityState.Loading)
    val state: StateFlow<MainActivityState> = _state.asStateFlow()

    private val _snackbarEvent = Channel<MainActivityEvent>()
    val snackbarEvent = _snackbarEvent.receiveAsFlow()

    init {
        getApplications()
    }

    fun onIconClick() {
        viewModelScope.launch {
            _snackbarEvent.send(MainActivityEvent.SnackbarShown(SNACKBAR_TEXT))
        }
    }

    private fun getApplications() {
        viewModelScope.launch {
            _state.value = MainActivityState.Loading
            delay(timeMillis = DELAY_VALUE)

            val applications: List<ApplicationData> = useCase.invoke()
            _state.value = MainActivityState.Content(applications)
        }
    }
}