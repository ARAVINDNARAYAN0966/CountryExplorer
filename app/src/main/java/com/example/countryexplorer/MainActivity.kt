package com.example.countryexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.countryexplorer.api.Country
import com.example.countryexplorer.ui.theme.CountryExplorerTheme
import com.example.countryexplorer.viewmodel.CountryViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            CountryExplorerTheme {
                CountryScreen()
            }
        }
    }
}

@Composable
fun CountryScreen(
    viewModel: CountryViewModel = viewModel()
) {

    val countries by viewModel.countries.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    var selectedCountry by remember {
        mutableStateOf<Country?>(null)
    }

    LaunchedEffect(Unit) {
        viewModel.loadCountries()
    }

    when {

        // -------------------------
        // LOADING
        // -------------------------
        isLoading -> {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                CircularProgressIndicator()

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    text = "Loading all countries..."
                )
            }
        }

        // -------------------------
        // ERROR
        // -------------------------
        error != null -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Failed to load countries",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = error ?: "Unknown error"
                )
            }
        }

        // -------------------------
        // EMPTY
        // -------------------------
        countries.isEmpty() -> {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "No countries found"
                )
            }
        }

        // -------------------------
        // COUNTRY LIST
        // -------------------------
        else -> {

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                // -------------------------
                // HEADER
                // -------------------------

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 40.dp,
                            bottom = 12.dp
                        )
                ) {

                    Text(
                        text = "Country Explorer",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = "${countries.size} countries",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    if (selectedCountry != null) {

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        Text(
                            text = "Selected: ${selectedCountry!!.names.common}",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                // -------------------------
                // SCROLLABLE LIST
                // -------------------------

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),

                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        top = 8.dp,
                        bottom = 24.dp
                    ),

                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(
                        items = countries,
                        key = { country ->
                            country.names.common
                        }
                    ) { country ->

                        CountryCard(
                            country = country,
                            isSelected = selectedCountry == country,
                            onClick = {
                                selectedCountry = country
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CountryCard(
    country: Country,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(
            containerColor =
                if (isSelected) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                }
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 8.dp else 4.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {

            // -------------------------
            // COUNTRY NAME
            // -------------------------

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = country.names.common,
                    style = MaterialTheme.typography.titleLarge
                )

                if (isSelected) {

                    Text(
                        text = "✓",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            // -------------------------
            // REGION
            // -------------------------

            Text(
                text = "Region: ${country.region ?: "Unknown"}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            // -------------------------
            // POPULATION
            // -------------------------

            Text(
                text = "Population: ${
                    country.population?.let {
                        "%,d".format(it)
                    } ?: "Unknown"
                }",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            // -------------------------
            // CAPITAL
            // -------------------------

            Text(
                text = "Capital: ${
                    country.capital?.firstOrNull() ?: "Unknown"
                }",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}