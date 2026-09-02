package com.example.countryexplorer.api

import retrofit2.http.GET
import retrofit2.http.Query

interface CountryApi {

    @GET("countries/v5")
    suspend fun getCountries(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0
    ): CountryResponse
}