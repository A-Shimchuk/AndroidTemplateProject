package com.example.androidtemplateproject.screens.mainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtemplateproject.screens.mainActivity.repositories.AppRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {
    private val repository = AppRepository()
    private val _state = MutableStateFlow(MainActivityState())
    val state: StateFlow<MainActivityState> = _state.asStateFlow()

    init {
        makeAppRequest()
    }

    fun makeAppRequest() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true,
            )
            // Иллюзия деятельности
            delay(2000)

            _state.value = _state.value.copy(
                items = repository.getApplications(),
                isLoading = false
            )
        }
    }
}