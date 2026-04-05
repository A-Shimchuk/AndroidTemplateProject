package com.example.androidtemplateproject.screens.detailScreen.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtemplateproject.screens.detailScreen.domain.GetApplicationDetailsUseCase
import com.example.androidtemplateproject.screens.appList.domain.ApplicationData
import com.example.androidtemplateproject.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val useCase: GetApplicationDetailsUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = MutableStateFlow<DetailScreenState>(DetailScreenState.Loading)
    val state: StateFlow<DetailScreenState> = _state.asStateFlow()

    init {
        val id: String = checkNotNull(savedStateHandle[Screen.Detail.ARG_APPLICATION_ID])
        getAppById(id)
    }

    private fun getAppById(id: String) {
        viewModelScope.launch {
            _state.value = DetailScreenState.Loading
            try {
                val application: ApplicationData? = useCase(id)

                application?.let {
                    _state.value = DetailScreenState.Content(application)
                } ?: run {
                    _state.value = DetailScreenState.Error
                }
            } catch (e: Exception) {
                _state.value = DetailScreenState.Error
                // Можно добавить логирование ошибки
            }
        }
    }
}