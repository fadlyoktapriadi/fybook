package com.fyooo.fybook.ui.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fyooo.fybook.R
import com.fyooo.fybook.data.local.entity.CartBookEntity
import com.fyooo.fybook.data.model.Book
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CartItem(
    book: CartBookEntity,
    deleteBook: (CartBookEntity) -> Unit

) {
    var quantity by remember { mutableStateOf(book.quantity!!) }

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
                .fillMaxWidth()

        ) {
            AsyncImage(
                model = book.coverUrl,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(70.dp)
            )
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                modifier = Modifier
                    .padding(8.dp)
                    .width(170.dp)
                ){
                    Column {
                        Text(
                            text = book.title?:"",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            text = formatCurrency(book.price!!),
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Text(
                            text = "Subtotal: ${formatCurrency(book.price!! * quantity)}",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
            Text(
                text = "x$quantity",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(8.dp)
            )

            IconButton(onClick = {
                deleteBook(book)
            }) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Back"
                )
            }


        }
    }
}


@Composable
@Preview(showBackground = true)
fun CartItemPreview() {
    CartItem(
        book = CartBookEntity(
            id = 1,
            bookId = 1,
            title = "Meditations",
            price = 10000,
            coverUrl = "https://fybook.s3.ap-southeast-1.amazonaws.com/meditations.jpg",
            quantity = 1
        ),
        deleteBook = {}
    )
}

