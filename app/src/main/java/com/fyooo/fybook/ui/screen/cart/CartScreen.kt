package com.fyooo.fybook.ui.screen.cart

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fyooo.fybook.data.local.entity.CartBookEntity
import com.fyooo.fybook.ui.common.UiState
import com.fyooo.fybook.ui.screen.components.CartItem
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navigateBack: () -> Unit,
    navigateToCheckout: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel()
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Cart") },
                navigationIcon = {
                    IconButton(onClick = {
                        navigateBack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                ElevatedButton(
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = MaterialTheme.colorScheme.primary,
                        disabledContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    onClick = {
                        navigateToCheckout()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Checkout"
                    )
                    Text(text = "Checkout")
                }
            }
        }
    ){ paddingValues ->

        val uiState = viewModel.cartBooksState.collectAsState(initial = UiState.Loading).value

        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllCartBooks()
            }
            is UiState.Success -> {
                CartContent(
                    listBookCart = uiState.data,
                    deleteBook = { cartBook ->
                        viewModel.deleteBookFromCart(cartBook)
                    },
                    modifier = modifier.padding(paddingValues)
                )
            }
            is UiState.Error -> {
                Text(text = "Error: ${uiState.errorMessage}", modifier = Modifier.padding(paddingValues))
            }
        }
    }
}

@Composable
fun CartContent(
    modifier: Modifier = Modifier,
    listBookCart: List<CartBookEntity> = emptyList(),
    deleteBook: (CartBookEntity) -> Unit
){
    Column(
    ) {
        if (listBookCart.isEmpty()) {
            Text(text = "Your cart is empty", modifier = modifier.padding(16.dp))
        } else {
            LazyColumn(
                modifier = modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listBookCart) { cartBook ->
                    CartItem(book = cartBook,
                        deleteBook = { book ->
                            deleteBook(book)
                        },
                    )
                }
            }
    }




    }




}