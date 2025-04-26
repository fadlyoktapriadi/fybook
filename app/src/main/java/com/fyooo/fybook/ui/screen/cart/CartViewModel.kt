package com.fyooo.fybook.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fyooo.core.data.BookRepository
import com.fyooo.core.data.local.entity.CartBookEntity
import com.fyooo.fybook.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CartViewModel(private val repository: BookRepository) : ViewModel() {

    private val _cartBooksState = MutableStateFlow<UiState<List<CartBookEntity>>>(UiState.Loading)
    val cartBooksState: StateFlow<UiState<List<CartBookEntity>>> = _cartBooksState

    fun getAllCartBooks() {
        viewModelScope.launch {
            repository.getAllCartBooks()
                .catch { exception ->
                    _cartBooksState.value = UiState.Error(exception.message ?: "Unknown error")
                }
                .collect { books ->
                    _cartBooksState.value = UiState.Success(books)
                }
        }
    }

    fun deleteBookFromCart(cartBook: CartBookEntity) {
        viewModelScope.launch {
            repository.deleteCartBook(cartBook)
                .catch { exception ->
                    _cartBooksState.value = UiState.Error(exception.message ?: "Unknown error")
                }
                .collect {
                    getAllCartBooks()
                }
        }
    }

}