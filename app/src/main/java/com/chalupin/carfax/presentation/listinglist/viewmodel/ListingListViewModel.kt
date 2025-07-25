package com.chalupin.carfax.presentation.listinglist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chalupin.carfax.domain.model.Listing
import com.chalupin.carfax.domain.usecase.GetListingsUseCase
import com.chalupin.carfax.presentation.listinglist.util.ListingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingListViewModel @Inject constructor(
    private val getListingsUseCase: GetListingsUseCase
) : ViewModel() {

    private val _listings = MutableStateFlow<List<Listing>>(emptyList())
    val listings: StateFlow<List<Listing>> = _listings

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchListings()
    }

    private fun fetchListings() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            getListingsUseCase()
                .catch { e ->
                    _error.value = "Failed to load listings: ${e.message}"
                    _isLoading.value = false
                }
                .collectLatest { result ->
                    when (result) {
                        is ListingsState.Loading -> {
                            _isLoading.value = true
                            _error.value = null
                        }

                        is ListingsState.Success -> {
                            _isLoading.value = false
                            _error.value = null
                            _listings.value = result.data ?: emptyList()
                        }

                        is ListingsState.Error -> {
                            _isLoading.value = false
                            _error.value = result.message
                        }

                        is ListingsState.Offline -> {
                            _isLoading.value = false
                            result.data?.let {
                                _listings.value = it
                            }
                        }
                    }
                }
        }
    }
}