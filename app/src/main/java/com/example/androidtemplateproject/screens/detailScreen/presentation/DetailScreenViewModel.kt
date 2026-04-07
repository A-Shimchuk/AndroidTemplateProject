package com.example.androidtemplateproject.screens.detailScreen.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtemplateproject.screens.detailScreen.domain.GetApplicationDetailsUseCase
import com.example.androidtemplateproject.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val useCase: GetApplicationDetailsUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = MutableStateFlow<DetailScreenState>(DetailScreenState.Loading)
    val state: StateFlow<DetailScreenState> = _state.asStateFlow()

    private val appId: String = checkNotNull(savedStateHandle[Screen.Detail.ARG_APPLICATION_ID])

    init {
        loadDataAndObserve()
    }

    private fun loadDataAndObserve() {
        viewModelScope.launch {
            _state.value = DetailScreenState.Loading

            if (useCase(appId) == null) {
                _state.value = DetailScreenState.Error
                return@launch
            }

            useCase.observeAppDetails(appId)
                .catch { _state.value = DetailScreenState.Error }
                .collect { appDetails ->
                    appDetails?.let {
                        _state.value = DetailScreenState.Content(it)
                    }
                }
        }
    }

    fun toggleWishlist() {
        viewModelScope.launch {
            useCase.toggleWishlist(appId)
        }
    }
}