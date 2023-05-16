package com.example.geographicatlas.domain.repository

import com.example.geographicatlas.data.remote.dto.CountryDto

interface CountryRepository {

    suspend fun getAllCountries(): List<CountryDto>

    suspend fun getCountryByCca2(cca2: String): List<CountryDto>
}