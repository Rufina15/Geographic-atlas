package com.example.geographicatlas.presentation

sealed class Screen(val route: String) {
    object CountriesListScreen : Screen("countries_list_screen")
    object CountryDetailsScreen : Screen("country_details_screen")
}
