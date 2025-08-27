package com.chalupin.autos.presentation.listingdetails.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chalupin.autos.domain.usecase.GetListingDetailsUseCase
import com.chalupin.autos.domain.util.ListingDetailsResponse
import com.chalupin.autos.presentation.listingdetails.util.ListingDetailsEvent
import com.chalupin.autos.presentation.listingdetails.util.ListingDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingDetailViewModel @Inject constructor(
    private val getListingDetailsUseCase: GetListingDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<ListingDetailsState>(ListingDetailsState.Loading)
    val uiState: StateFlow<ListingDetailsState> = _uiState.asStateFlow()

    val vin = savedStateHandle.get<String>("listing_vin")

    init {
        vin?.let {
            handleEvent(ListingDetailsEvent.LoadListingDetailsEvent(vin = it))
        }
    }

    private fun handleEvent(event: ListingDetailsEvent) {
        when (event) {
            is ListingDetailsEvent.LoadListingDetailsEvent -> fetchListingDetails(event.vin)
        }
    }

    private fun fetchListingDetails(vin: String) {
        viewModelScope.launch {
            _uiState.value = ListingDetailsState.Loading
            try {
                when (val result = getListingDetailsUseCase(vin)) {
                    is ListingDetailsResponse.Success -> {
                        _uiState.value = ListingDetailsState.Success(result.data)
                    }

                    is ListingDetailsResponse.Error -> {
                        _uiState.value = ListingDetailsState.Error(result.exception)
                    }
                }
            } catch (e: Exception) {
                _uiState.value = ListingDetailsState.Error(e)
            }
        }
    }
}