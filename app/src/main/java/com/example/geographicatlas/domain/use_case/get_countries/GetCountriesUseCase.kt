package com.example.geographicatlas.domain.use_case.get_countries

import com.example.geographicatlas.common.Resource
import com.example.geographicatlas.data.remote.dto.toCountry
import com.example.geographicatlas.domain.model.Country
import com.example.geographicatlas.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.HttpRetryException
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val repository: CountryRepository
) {
    operator fun invoke(): Flow<Resource<Map<String, List<Country>>>> = flow {
        try {
            emit(Resource.Loading<Map<String, List<Country>>>())
            val continents =
                repository.getAllCountries().map { it.toCountry() }.sortedBy { it.name }
                    .groupBy { it.continent }.toSortedMap()
            emit(Resource.Success<Map<String, List<Country>>>(continents))

        } catch (e: HttpRetryException) {
            emit(
                Resource.Error<Map<String, List<Country>>>(
                    e.localizedMessage ?: "Error while retrieving countries list"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<Map<String, List<Country>>>("Connection problem while retrieving countries list"))
        }
    }
}