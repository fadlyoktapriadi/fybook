package com.fyooo.fybook.ui.screen.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fyooo.fybook.data.api.response.ResultsItemProvince
import com.fyooo.fybook.data.local.entity.CartBookEntity
import com.fyooo.fybook.ui.common.UiState
import com.fyooo.fybook.ui.screen.cart.CartViewModel
import com.fyooo.fybook.ui.screen.components.CartItem
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = koinViewModel()
) {
    val provinces by viewModel.provinces.collectAsState()
    val cities by viewModel.cities.collectAsState()
    val shippingCost by viewModel.shippingCost.collectAsState()

    var expandedProvince by remember { mutableStateOf(false) }
    var expandedCity by remember { mutableStateOf(false) }
    var expandedShippingCost by remember { mutableStateOf(false) }

    var selectedProvince by remember { mutableStateOf<ResultsItemProvince?>(null) }
    var selectedCity by remember { mutableStateOf("") }
    var selectedShippingCost by remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
        viewModel.fetchProvinces()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Checkout") },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Province Dropdown
            Text(text = "Select Province", style = MaterialTheme.typography.bodyLarge)
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = selectedProvince?.province ?: "Choose a province",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { expandedProvince = true }
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                )

                DropdownMenu(
                    expanded = expandedProvince,
                    onDismissRequest = { expandedProvince = false }
                ) {
                    provinces?.forEach { province ->
                        DropdownMenuItem(
                            text = { Text(province.province) },
                            onClick = {
                                selectedProvince = province
                                expandedProvince = false
                                viewModel.fetchCities(province.provinceId)
                            }
                        )
                    }
                }
            }

            // City Dropdown
            Text(text = "Select City", style = MaterialTheme.typography.bodyLarge)
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = if (selectedCity.isEmpty()) "Choose a city" else selectedCity,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { expandedCity = true }
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                )

                DropdownMenu(
                    expanded = expandedCity,
                    onDismissRequest = { expandedCity = false }
                ) {
                    cities.forEach { city ->
                        DropdownMenuItem(
                            text = { Text(city.cityName) },
                            onClick = {
                                selectedCity = city.cityName
                                expandedCity = false
                                viewModel.getShippingCost(
                                    destination = city.cityId,
                                )
                            }
                        )
                    }
                }
            }

            Text(text = "Select Expedition", style = MaterialTheme.typography.bodyLarge)
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = if (selectedShippingCost.isEmpty()) "Choose an expedition" else selectedShippingCost,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { expandedShippingCost = true }
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                )

                DropdownMenu(
                    expanded = expandedShippingCost,
                    onDismissRequest = { expandedShippingCost = false }
                ) {
                    shippingCost.forEach { ship ->
                        ship.costs.forEach { cost ->
                            DropdownMenuItem(
                                text = { Text("${cost.service} - ${cost.cost[0].value}") },
                                onClick = {
                                    selectedShippingCost = "${cost.service} - ${cost.cost[0].value}"
                                    expandedShippingCost = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}