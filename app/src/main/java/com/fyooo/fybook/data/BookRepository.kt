package com.fyooo.fybook.data

import com.fyooo.fybook.data.model.Book
import com.fyooo.fybook.data.source.BookDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookRepository {

    fun getAllBooks(): Flow<List<Book>> = flow {
        emit(BookDataSource.dummyBook)
    }
    fun getBookById(id: Long): Flow<Book> = flow {
        val book = BookDataSource.dummyBook.find { it.id == id }
        if (book != null) {
            emit(book)
        } else {
            throw Exception("Book not found")
        }
    }

}