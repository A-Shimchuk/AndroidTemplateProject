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

class MainActivityViewModel : ViewModel() {
    private companion object LocalConstants{
        // FIXME: - Вынести
        val snackbarText = "Произошел показ снекбара"
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

    private val _snackbarEvent = Channel<String>()
    val snackbarEvent = _snackbarEvent.receiveAsFlow()

    init {
        getApplications()
    }

    fun onIconClick(appId: String) {
        viewModelScope.launch {
            _snackbarEvent.send(snackbarText)
        }
    }

    private fun getApplications() {
        viewModelScope.launch {
            _state.value = MainActivityState.Loading
            delay(timeMillis = 300)

            val applications: List<ApplicationData> = useCase.invoke()
            applications.let {
                _state.value = MainActivityState.Content(applications)
            } ?: run {
                _state.value = MainActivityState.Error
            }
        }
    }
}