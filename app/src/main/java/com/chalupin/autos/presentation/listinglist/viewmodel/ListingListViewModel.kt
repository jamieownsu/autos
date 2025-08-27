package com.chalupin.autos.presentation.listinglist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chalupin.autos.domain.usecase.GetListingsUseCase
import com.chalupin.autos.domain.util.ListingsResponse
import com.chalupin.autos.presentation.listinglist.util.ListingsEvent
import com.chalupin.autos.presentation.listinglist.util.ListingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingListViewModel @Inject constructor(
    private val getListingsUseCase: GetListingsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ListingsState>(ListingsState.Loading)
    val uiState: StateFlow<ListingsState> = _uiState.asStateFlow()

    init {
        handleEvent(ListingsEvent.LoadListingsEvent)
    }

    fun handleEvent(event: ListingsEvent) {
        when (event) {
            ListingsEvent.LoadListingsEvent -> fetchListings()
        }
    }

    private fun fetchListings() {
        viewModelScope.launch {
            _uiState.value = ListingsState.Loading
            try {
                when (val result = getListingsUseCase()) {
                    is ListingsResponse.Success -> {
                        _uiState.value = ListingsState.Success(result.data)
                    }

                    is ListingsResponse.Offline -> {
                        _uiState.value = ListingsState.Success(result.data, true)
                    }

                    is ListingsResponse.Error -> {
                        _uiState.value = ListingsState.Error(result.exception)
                    }
                }
            } catch (e: Exception) {
                _uiState.value = ListingsState.Error(e)
            }
        }
    }
}