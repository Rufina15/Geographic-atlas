package com.example.geographicatlas.presentation.country_details

import com.example.geographicatlas.domain.model.Country


data class CountryDetailsState(
    val isLoading: Boolean = false,
    val country: Country? = null,
    val error: String = ""
)
