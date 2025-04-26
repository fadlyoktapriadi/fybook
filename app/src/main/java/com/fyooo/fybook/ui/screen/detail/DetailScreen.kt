package com.fyooo.fybook.ui.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.compose.FybookTheme
import com.fyooo.core.data.local.entity.CartBookEntity
import com.fyooo.core.data.model.Book
import com.fyooo.core.data.source.BookDataSource
import com.fyooo.fybook.ui.common.UiState
import com.fyooo.fybook.ui.screen.components.ProductQuantityCounter
import com.fyooo.fybook.ui.screen.components.formatCurrency
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    id: Long,
    viewModel: DetailViewModel = koinViewModel(),
    navigateBack: () -> Unit,
    navigateToCart: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Book Details") },
                actions = {
                    IconButton(onClick = {
                        navigateToCart()
                    }) {
                        Icon(Icons.Outlined.ShoppingCart, contentDescription = "Cart")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }

            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        when (val uiState = viewModel.uiState.collectAsState(initial = UiState.Loading).value) {
            is UiState.Loading -> {
                viewModel.getBookId(id)
            }
            is UiState.Success -> {
                DetailContent(
                    book = uiState.data,
                    modifier = Modifier.padding(innerPadding),
                    insertToCart = { quantity ->
                        val cartBook = CartBookEntity(
                            id = null,
                            bookId = uiState.data.id,
                            title = uiState.data.title,
                            price = uiState.data.price,
                            coverUrl = uiState.data.coverUrl,
                            quantity = quantity
                        )
                        viewModel.insertCartBook(cartBook, snackbarHostState)
                    }
                )
            }
            is UiState.Error -> {
                Text(text = "Error: ${uiState.errorMessage}", modifier = Modifier.padding(16.dp))
            }
        }
    }
}


@Composable
fun DetailContent(
    book: Book,
    modifier: Modifier = Modifier,
    insertToCart: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()) // Enable vertical scrolling
    ) {
        var quantity by remember { mutableIntStateOf(1) }

        AsyncImage(
            model = book.coverUrl,
            contentDescription = "Book Cover",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .size(240.dp)
        )

        Card(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 8.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(14.dp)
            ) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = formatCurrency(book.price),
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }

        Card(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 8.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 14.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Categories: ${book.categories}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Rating: ${book.rating} / 5 ⭐",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Author: ${book.author}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Published: ${book.publishedDate}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Publisher: ${book.publisher}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "ISBN: ${book.isbn}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Description:",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = book.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }

        Card(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 8.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
//                    .align(Alignment.CenterHorizontally)

            ) {
                Text(
                    text = "Total: ${formatCurrency(book.price * quantity)}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )
                ProductQuantityCounter(
                    quantity = quantity,
                    onIncrease = { quantity++ },
                    onDecrease = { if (quantity > 0) quantity-- },
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        ElevatedButton(
            onClick = {
                insertToCart(quantity)
            },
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            modifier = Modifier.padding(8.dp)
                .fillMaxWidth()

        ) {
            Text(
                text = "Add to Cart",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(8.dp)
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun DetailContentPreview() {
    val book = BookDataSource.dummyBook.firstOrNull { it.id == 1.toLong() }
        ?: Book(
            id = 1,
            title = "Dummy Book",
            author = "Dummy Author",
            description = "Dummy Description",
            coverUrl = "",
            rating = 0f,
            publishedDate = "",
            publisher = "",
            isbn = "",
            categories = "",
            price = 0
        )
    FybookTheme {
        DetailContent(book, insertToCart = {})

    }
}
