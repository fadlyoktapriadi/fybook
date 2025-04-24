package com.fyooo.fybook.ui.screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.compose.FybookTheme
import com.fyooo.fybook.data.model.Book
import com.fyooo.fybook.data.source.BookDataSource
import java.text.NumberFormat
import java.util.Locale

@Composable
fun BookItem(
    book : Book,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        onClick = { }
    ) {
        Column {
            AsyncImage(
                model = book.coverUrl,
                contentDescription = "Book Cover",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(180.dp)
            )
            Column(
                modifier = modifier.padding(8.dp)
            ) {
                Text(
                    text = book.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 2.dp)
                )
                Text(
                    text = book.categories,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = formatCurrency(book.price),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }
        }
    }
}

fun formatCurrency(amount: Int): String {
    val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    format.minimumFractionDigits = 0
    format.maximumFractionDigits = 0
    return format.format(amount)
}

@Composable
@Preview(showBackground = true)
fun RewardItemPreview() {
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
        BookItem(book)
    }
}