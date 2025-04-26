package com.fyooo.core.data

import com.fyooo.core.data.api.ApiService
import com.fyooo.core.data.api.response.CityResponse
import com.fyooo.core.data.api.response.CostResponse
import com.fyooo.core.data.api.response.ProvinceResponse
import com.fyooo.core.data.local.database.CartDao
import com.fyooo.core.data.local.entity.CartBookEntity
import com.fyooo.core.data.model.Book
import com.fyooo.core.data.source.BookDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class BookRepository(private val cartDao: CartDao, private val apiService: ApiService) {

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

    fun insertCartBook(cartBook: CartBookEntity): Flow<Unit> = flow {
        cartDao.insertCartBook(cartBook)
        emit(Unit)
    }

    fun getAllCartBooks(): Flow<List<CartBookEntity>> = flow {
        val cartBooks = cartDao.getAllCartBooks()
        emit(cartBooks)
    }

    fun deleteCartBook(cartBook: CartBookEntity): Flow<Unit> = flow {
        cartDao.deleteCartBook(cartBook)
        emit(Unit)
    }

    fun getProvinces(): Flow<ProvinceResponse> = flow {
        val response = apiService.getProvinces()
        emit(response)
    }

    fun getCities(provinceId: String): Flow<CityResponse> = flow {
        val response = apiService.getCities(provinceId)
        emit(response)
    }

    fun getShippingCost(
        origin: String,
        destination: String,
        weight: Int,
        courier: String
    ): Flow<CostResponse> = flow {
        val response = apiService.getShippingCost(origin, destination, weight, courier)
        emit(response)
    }

}