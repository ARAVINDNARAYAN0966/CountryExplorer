package com.example.countryexplorer.api

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("data")
    val data: CountryData
)

data class CountryData(
    @SerializedName("objects")
    val objects: List<Country>
)

data class Country(
    @SerializedName("names")
    val names: Names,

    @SerializedName("flag")
    val flag: Flag?,

    @SerializedName("capital")
    val capital: List<String>?,

    @SerializedName("region")
    val region: String?,

    @SerializedName("population")
    val population: Long?
)

data class Names(
    @SerializedName("common")
    val common: String
)

data class Flag(
    @SerializedName("emoji")
    val emoji: String?
)