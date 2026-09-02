package com.example.countryexplorer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countryexplorer.api.ApiClient
import com.example.countryexplorer.api.Country
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

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