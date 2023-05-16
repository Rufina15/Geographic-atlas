package com.example.geographicatlas.presentation.country_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.geographicatlas.presentation.Screen
import com.example.geographicatlas.presentation.country_list.components.CountryListItem

@ExperimentalMaterialApi
@Composable
fun CountryListScreen(
    navController: NavController,
    viewModel: CountryListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            TopAppBar(
                elevation = 2.dp,
                title = {
                    Text("World countries", color = MaterialTheme.colors.primary)
                },
                backgroundColor = MaterialTheme.colors.background
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(state.countries.toList(), itemContent = {

                    Column(Modifier.padding(bottom = 4.dp)) {
                        Text(
                            text = it.first.uppercase(),
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray,
                            fontSize = 17.sp,
                            letterSpacing = 3.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        it.second.forEach { country ->
                            CountryListItem(country = country, onItemClick = {
                                navController.navigate(Screen.CountryDetailsScreen.route + "/${country.id}")
                            })
                        }
                    }
                })
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}