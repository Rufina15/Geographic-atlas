package com.example.geographicatlas.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.geographicatlas.presentation.country_details.CountryDetailsScreen
import com.example.geographicatlas.presentation.country_list.CountryListScreen
import com.example.geographicatlas.presentation.ui.theme.CountryAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountryAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CountriesListScreen.route
                    ) {
                        composable(
                            route = Screen.CountriesListScreen.route
                        ) {
                            CountryListScreen(navController)
                        }
                        composable(
                            route = Screen.CountryDetailsScreen.route + "/{cca2}"
                        ) {
                            CountryDetailsScreen(navController)
                        }
                    }
                }
            }
        }
    }
}