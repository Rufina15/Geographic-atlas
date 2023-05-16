package com.example.geographicatlas.data.remote

import com.example.geographicatlas.data.remote.dto.CountryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RestCountriesApi {
    @GET("/v3.1/all")
    suspend fun getAllCountries(): List<CountryDto>

    @GET("/v3.1/alpha/{cca2}")
    suspend fun getCountryByCca2(@Path("cca2") cca2: String): List<CountryDto>
}