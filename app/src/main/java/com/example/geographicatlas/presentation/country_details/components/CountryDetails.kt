package com.example.geographicatlas.presentation.country_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.geographicatlas.domain.model.Country

@Composable
fun CountryDetails(
    country: Country,
    navController: NavController
) {
    Column(Modifier.fillMaxWidth()) {

        TopAppBar(
            elevation = 2.dp,
            modifier = Modifier.padding(bottom = 10.dp),
            title = {
                Text(country.name, color = MaterialTheme.colors.primary)
            },
            backgroundColor = MaterialTheme.colors.background,
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            }
        )

        Column(
            Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = country.flag,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            DrawBullet("Capital:", listOf(country.capital))
            DrawBullet("Capital coordinates:", listOf(country.coordinates))
            DrawBullet("Population:", listOf(country.population))
            DrawBullet("Area:", listOf(country.area))
            DrawBullet("Currency:", country.currencies)
            DrawBullet("Region:", listOf(country.continent))
            DrawBullet("Timezones:", country.timezones)

        }
    }

}

@Composable
fun DrawBullet(title: String, description: List<String>) {
    Column(
        Modifier
            .padding(start = 16.dp)
            .padding(top = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Filled.Info, contentDescription = null,
                Modifier
                    .size(20.dp)
                    .padding(end = 12.dp)
            )
            Text(
                text = title,
                fontWeight = FontWeight.Light,
                color = Color.Gray
            )
        }
        description.forEach {
            Text(
                text = it,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}
