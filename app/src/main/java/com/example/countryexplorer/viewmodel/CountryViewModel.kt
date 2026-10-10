package com.example.countryexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countryexplorer.api.ApiClient
import com.example.countryexplorer.api.Country
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {

    // All countries
    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries

    // Loading state
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Error message
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // Search text
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    // Filter countries based on search
    val filteredCountries: StateFlow<List<Country>> =
        combine(_countries, _searchQuery) { countries, query ->

            if (query.isBlank()) {
                countries
            } else {
                countries.filter { country ->
                    country.names.common.contains(
                        query,
                        ignoreCase = true
                    )
                }
            }

        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Update search text
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    // Load countries
    fun loadCountries() {

        if (_isLoading.value) return

        viewModelScope.launch {

            _isLoading.value = true
            _error.value = null

            try {

                val allCountries = mutableListOf<Country>()

                var offset = 0
                val limit = 100

                while (true) {

                    val response = ApiClient.api.getCountries(
                        limit = limit,
                        offset = offset
                    )

                    val countries = response.data.objects

                    allCountries.addAll(countries)

                    if (countries.size < limit) {
                        break
                    }

                    offset += limit
                }

                _countries.value = allCountries

            } catch (e: Exception) {

                _error.value = e.message ?: "Unknown error"

            } finally {

                _isLoading.value = false
            }
        }
    }
}