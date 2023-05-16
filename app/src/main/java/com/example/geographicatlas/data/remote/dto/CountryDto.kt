package com.example.geographicatlas.data.remote.dto

import com.example.geographicatlas.domain.model.Country
import kotlin.math.roundToInt


data class CountryDto(
    val capital: List<String>?,
    val capitalInfo: CapitalInfo?,
    val cca2: String,
    val currencies: Map<String, Currency>?,
    val flags: Flags,
    val name: Name,
    val population: Int,
    val subregion: String,
    val timezones: List<String>,
    val area: Double,
    val continents: List<String>
)

fun CountryDto.toCountry(): Country {
    return Country(
        id = cca2,
        name = name.common,
        capital = capital?.first() ?: "",
        population = population.toString(),
        area = "${area.roundToInt()} km²",
        currencies = currencies?.map { "${it.value.name} (${it.value.symbol}) (${it.key})" }
            ?: emptyList(),
        flag = flags.png,
        continent = continents.first(),
        timezones = timezones,
        coordinates = coordinatesToString(capitalInfo)
    )
}

private fun coordinatesToString(capitalInfo: CapitalInfo?): String {
    val latitude = capitalInfo?.latlng?.getOrNull(0).toString().split(".")
    val longitude = capitalInfo?.latlng?.getOrNull(1).toString().split(".")
    return "${latitude.getOrNull(0)}°${latitude.getOrNull(1)}′, ${longitude.getOrNull(0)}°${
        longitude.getOrNull(
            1
        )
    }′"
}