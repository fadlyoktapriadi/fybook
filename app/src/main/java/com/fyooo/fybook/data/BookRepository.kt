package com.fyooo.fybook.data

import com.fyooo.fybook.data.model.Book
import com.fyooo.fybook.data.source.BookDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookRepository {

    fun getAllBooks(): Flow<List<Book>> = flow {
        emit(BookDataSource.dummyBook)
    }


//    companion object {
//        @Volatile
//        private var instance: BookRepository? = null
//
//        fun getInstance(): BookRepository =
//            instance ?: synchronized(this) {
//                BookRepository().apply {
//                    instance = this
//                }
//            }
//    }
}