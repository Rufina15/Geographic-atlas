package com.example.geographicatlas.domain.use_case.get_country

import com.example.geographicatlas.common.Resource
import com.example.geographicatlas.data.remote.dto.toCountry
import com.example.geographicatlas.domain.model.Country
import com.example.geographicatlas.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.HttpRetryException
import javax.inject.Inject

class GetCountryUseCase @Inject constructor(
    private val repository: CountryRepository
) {
    operator fun invoke(cca2: String): Flow<Resource<Country>> = flow {
        try {
            emit(Resource.Loading<Country>())
            val country = repository.getCountryByCca2(cca2)[0].toCountry()
            emit(Resource.Success<Country>(country))

        } catch (e: HttpRetryException) {
            emit(
                Resource.Error<Country>(
                    e.localizedMessage ?: "Error while retrieving countries list"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<Country>("Connection problem while retrieving countries list"))
        }
    }
}