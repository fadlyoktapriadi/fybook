package com.fyooo.fybook.ui.screen.Home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fyooo.fybook.data.model.Book
import com.fyooo.fybook.ui.BookItem
import com.fyooo.fybook.ui.common.UiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {

            is UiState.Loading -> {
                viewModel.fetchBooks()
            }
            is UiState.Success<*> -> {
                val listBook = uiState.data as List<Book>
                HomeContent(
                    listBook = listBook,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    listBook: List<Book>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    Column {
        Text(
            text = "Best Seller \uD83D\uDD25",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ) {
            items(listBook) { data ->
                BookItem(book = data)
            }
        }

    }
    }