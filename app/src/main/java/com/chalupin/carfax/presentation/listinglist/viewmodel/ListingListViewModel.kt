package com.chalupin.carfax.presentation.listinglist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chalupin.carfax.domain.usecase.GetListingsUseCase
import com.chalupin.carfax.domain.util.ListingsResponse
import com.chalupin.carfax.presentation.listinglist.util.ListingsEvent
import com.chalupin.carfax.presentation.listinglist.util.ListingsState
import com.chalupin.carfax.presentation.listinglist.util.ListingsState.Error
import com.chalupin.carfax.presentation.listinglist.util.ListingsState.Loading
import com.chalupin.carfax.presentation.listinglist.util.ListingsState.Success
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

    private val _uiState = MutableStateFlow<ListingsState>(Loading)
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
            _uiState.value = Loading
            try {
                when (val result = getListingsUseCase()) {
                    is ListingsResponse.Success -> {
                        _uiState.value = Success(result.data)
                    }

                    is ListingsResponse.Offline -> {
                        _uiState.value = Success(result.data, true)
                    }

                    is ListingsResponse.Error -> {
                        _uiState.value = Error(result.exception)
                    }
                }
            } catch (e: Exception) {
                _uiState.value = Error(e)
            }
        }
    }
}