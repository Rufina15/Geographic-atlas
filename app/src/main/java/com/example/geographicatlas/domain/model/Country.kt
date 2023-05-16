package com.example.geographicatlas.domain.model

import java.io.Serializable

data class Country(
    val id: String,
    val name: String,
    val capital: String,
    val population: String,
    val area: String,
    val currencies: List<String>,
    val flag: String,
    val continent: String,
    val timezones: List<String>,
    val coordinates: String
) : Serializable
