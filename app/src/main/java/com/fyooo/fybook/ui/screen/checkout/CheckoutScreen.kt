package com.fyooo.fybook.ui.screen.checkout

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.fyooo.core.data.api.response.ResultsItemProvince
import com.fyooo.fybook.ui.screen.components.formatCurrency
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
    val cartBooks by viewModel.cartBooks.collectAsState()

    var expandedProvince by remember { mutableStateOf(false) }
    var expandedCity by remember { mutableStateOf(false) }
    var expandedShippingCost by remember { mutableStateOf(false) }

    var selectedProvince by remember { mutableStateOf<ResultsItemProvince?>(null) }
    var selectedCity by remember { mutableStateOf("") }
    var selectedShippingCost by remember { mutableStateOf("") }
    var costShipping by remember { mutableIntStateOf(0) }

    var nameOrder by remember { mutableStateOf("") }
    var phoneOrder by remember { mutableStateOf("") }
    var addressOrder by remember { mutableStateOf("") }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchProvinces()
        viewModel.getAllCartBooks()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Checkout") },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
                .verticalScroll(rememberScrollState())
        ) {

            OutlinedTextField(
                value = nameOrder,
                onValueChange = {
                    nameOrder = it
                },
                label = { Text("Name") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(4.dp)
                    .clip(MaterialTheme.shapes.medium)
            )

            OutlinedTextField(
                value = phoneOrder,
                onValueChange = {
                    phoneOrder = it
                },
                label = { Text("Phone Number") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(4.dp)
                    .clip(MaterialTheme.shapes.medium)
            )

            OutlinedTextField(
                value = addressOrder,
                onValueChange = {
                    addressOrder = it
                },
                label = { Text("Address") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(4.dp)
                    .clip(MaterialTheme.shapes.medium)
            )

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
                            text = {
                                Text(
                                    province.province,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            },
                            onClick = {
                                selectedProvince = province
                                expandedProvince = false
                                viewModel.fetchCities(province.provinceId)
                            }
                        )
                    }
                }
            }

            Text(text = "Select City", style = MaterialTheme.typography.bodyLarge)
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = selectedCity.ifEmpty { "Choose a city" },
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

            Text(text = "Select Shipping", style = MaterialTheme.typography.bodyLarge)
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = selectedShippingCost.ifEmpty { "Choose an Shipping" },
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
                                    selectedShippingCost =
                                        "${cost.service} - ${formatCurrency(cost.cost[0].value)}"
                                    expandedShippingCost = false
                                    costShipping = cost.cost[0].value
                                }
                            )
                        }
                    }
                }
            }

            Card(
                modifier = Modifier.padding(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Text(
                        text = "Order Summary",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(8.dp)
                    )
                    cartBooks.forEach { book ->
                        Text(
                            text = "${book.title} - ${book.price?.let { formatCurrency(it) }}",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        Text(
                            text = "Quantity: ${book.quantity}",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = "Shipping Cost: ${formatCurrency(costShipping)}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                    Text(
                        text = "Total: ${formatCurrency(cartBooks.sumOf { it.price!! * it.quantity!! } + costShipping)}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }

            }
            ElevatedButton(
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.primary,
                    disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                onClick = {
                    if (nameOrder.isNotEmpty() && phoneOrder.isNotEmpty() && addressOrder.isNotEmpty() && selectedProvince != null && selectedCity.isNotEmpty() && selectedShippingCost.isNotEmpty()) {
                        val uri = String.format(
                            "https://api.whatsapp.com/send?phone=%s&text=%s",
                            6289514347848,
                            Uri.encode(
                                """
    *Order Book*

    Name: $nameOrder
    Phone: $phoneOrder
    Address: $addressOrder
    Province: ${selectedProvince?.province}
    City: $selectedCity
    Shipping Cost: ${formatCurrency(costShipping)}
    Total: ${formatCurrency(cartBooks.sumOf { it.price!! * it.quantity!! } + costShipping)}
    Books:
    ${
                                    cartBooks.joinToString("\n") {
                                        "${it.title} - ${it.price?.let { price -> formatCurrency(price) }}"
                                    }
                                }
    """.trimIndent()
                            )
                        )
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(text = "Submit Order")
            }
        }
    }
}

