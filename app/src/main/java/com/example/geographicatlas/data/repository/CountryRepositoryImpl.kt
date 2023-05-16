package com.example.geographicatlas.data.repository

import com.example.geographicatlas.data.remote.RestCountriesApi
import com.example.geographicatlas.data.remote.dto.CountryDto
import com.example.geographicatlas.domain.repository.CountryRepository
import javax.inject.Inject


class CountryRepositoryImpl @Inject constructor(
    private val api: RestCountriesApi
) : CountryRepository {
    override suspend fun getAllCountries(): List<CountryDto> {
        return api.getAllCountries()
    }

    override suspend fun getCountryByCca2(cca2: String): List<CountryDto> {
        return api.getCountryByCca2(cca2)
    }

}