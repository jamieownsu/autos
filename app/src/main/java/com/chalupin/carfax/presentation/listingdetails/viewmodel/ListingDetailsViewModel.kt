package com.chalupin.carfax.presentation.listingdetails.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chalupin.carfax.domain.model.Listing
import com.chalupin.carfax.domain.usecase.GetListingDetailsUseCase
import com.chalupin.carfax.presentation.listingdetails.util.ListingDetailsEvent
import com.chalupin.carfax.presentation.listingdetails.util.ListingDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingDetailViewModel @Inject constructor(
    private val getListingDetailsUseCase: GetListingDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _listingDetails = MutableStateFlow<Listing?>(null)
    val listingDetails: StateFlow<Listing?> = _listingDetails

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    val vin = savedStateHandle.get<String>("listing_vin")

    init {
        vin?.let {
            handleEvent(ListingDetailsEvent.LoadListingDetailsEvent(vin = it))
        } ?: run {
            _error.value = "VIN not provided."
            Log.e("ListingDetailViewModel", _error.value, Exception())
        }
    }

    private fun handleEvent(event: ListingDetailsEvent) {
        when (event) {
            is ListingDetailsEvent.LoadListingDetailsEvent -> fetchListingDetails(event.vin)
        }
    }

    private fun fetchListingDetails(vin: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            getListingDetailsUseCase(vin).catch { e ->
                _error.value = "Failed to load details: ${e.message}"
                _isLoading.value = false
                _listingDetails.value = null
                Log.e("ListingDetailViewModel", _error.value, e)
            }.collectLatest { result ->
                when (result) {
                    is ListingDetailsState.Loading -> {
                        _isLoading.value = true
                        _error.value = null
                    }

                    is ListingDetailsState.Success -> {
                        _isLoading.value = false
                        _error.value = null
                        _listingDetails.value = result.data
                    }

                    is ListingDetailsState.Error -> {
                        _isLoading.value = false
                        _error.value = result.message
                    }
                }
            }
        }
    }
}