package com.fyooo.fybook.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fyooo.core.data.model.Book
import com.fyooo.fybook.ui.common.UiState
import com.fyooo.fybook.ui.screen.components.BookItem
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    navigateToDetail: (Long) -> Unit,
    navigateToCart: () -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Fybook",
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.primary

                    )
                },
                actions = {
                    IconButton(onClick = {
                        navigateToCart()
                    }) {
                        Icon(Icons.Outlined.ShoppingCart, contentDescription = "Cart")
                    }
                }
            )
        },
    ) { innerPadding ->
        when (val uiState = viewModel.uiState.collectAsState(initial = UiState.Loading).value) {
            is UiState.Loading -> {
                viewModel.fetchBooks()
            }

            is UiState.Success -> {
                HomeContent(
                    listBook = uiState.data,
                    modifier = modifier.padding(innerPadding),
                    navigateToDetail = navigateToDetail
                )
            }

            is UiState.Error -> {
                Text(text = "Error: ${uiState.errorMessage}", modifier = Modifier.padding(16.dp))
            }
        }
    }

}

@Composable
fun HomeContent(
    listBook: List<Book>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ) {
            items(listBook) { data ->
                BookItem(book = data, modifier = Modifier.clickable {
                    navigateToDetail(data.id)
                })
            }
        }
}