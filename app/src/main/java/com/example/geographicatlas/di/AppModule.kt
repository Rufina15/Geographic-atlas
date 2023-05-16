package com.example.geographicatlas.di

import com.example.geographicatlas.common.Constants
import com.example.geographicatlas.data.remote.RestCountriesApi
import com.example.geographicatlas.data.repository.CountryRepositoryImpl
import com.example.geographicatlas.domain.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRestCountriesApi(): RestCountriesApi {
        return Retrofit.Builder()
            .baseUrl(Constants.REST_COUNTRIES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestCountriesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCountryRepository(api: RestCountriesApi): CountryRepository {
        return CountryRepositoryImpl(api)
    }
}