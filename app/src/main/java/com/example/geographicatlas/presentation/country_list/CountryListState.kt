package com.example.geographicatlas.presentation.country_list

import com.example.geographicatlas.domain.model.Country


data class CountryListState(
    val isLoading: Boolean = false,
    val countries: Map<String, List<Country>> = emptyMap(),
    val error: String = ""
)
