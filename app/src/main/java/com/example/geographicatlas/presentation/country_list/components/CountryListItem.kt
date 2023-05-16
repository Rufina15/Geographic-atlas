package com.example.geographicatlas.presentation.country_list.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.geographicatlas.domain.model.Country

val CARD_BACKGROUND_COLOR = Color(0xFFF7F8F9)

@Composable
fun CountryExpandedRow(title: String, data: String) {
    Row {
        Text(
            text = title,
            color = Color.Gray,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(end = 4.dp, bottom = 1.dp)
        )
        Text(
            text = data,
            color = Color.Black,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Normal
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun CountryListItem(
    country: Country,
    onItemClick: (Country) -> Unit
) {
    var expandState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(if (expandState) 180f else 0f)
    Card(
        backgroundColor = CARD_BACKGROUND_COLOR,
        shape = RoundedCornerShape(8.dp),
        onClick = {
            expandState = !expandState
        },

        modifier = Modifier
            .padding(bottom = 12.dp)
            .fillMaxWidth()
            .animateContentSize(tween(durationMillis = 300, easing = LinearOutSlowInEasing))
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Column(
                        modifier = Modifier
                            .align(CenterVertically)
                    ) {
                        AsyncImage(
                            model = country.flag,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .clip(RoundedCornerShape(4.dp))
                        )
                    }
                    Column(
                        modifier = Modifier
                            .align(CenterVertically)

                    ) {
                        Text(
                            text = country.name,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.primary,
                            fontSize = 17.sp
                        )
                        Text(
                            text = country.capital,
                            style = MaterialTheme.typography.body1,
                            color = Color.Gray
                        )
                    }
                }

                IconButton(
                    modifier = Modifier
                        .rotate(rotationState),
                    onClick = {
                        expandState = !expandState
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        tint = Color.Black,
                        contentDescription = null
                    )
                }

            }

            if (expandState) {
                Column {

                    CountryExpandedRow("Population:", country.population)
                    CountryExpandedRow("Area:", country.area)
                    CountryExpandedRow("Currencies:", country.currencies.joinToString(","))
                }
                Row(
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = { onItemClick(country) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "LEARN MORE",
                            letterSpacing = 2.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = Color(
                                0xFF6200EE
                            )
                        )
                    }
                }
            }
        }
    }
}
