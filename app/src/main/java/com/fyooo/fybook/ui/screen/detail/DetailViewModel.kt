package com.fyooo.fybook.ui.screen.detail
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fyooo.core.data.BookRepository
import com.fyooo.core.data.local.entity.CartBookEntity
import com.fyooo.core.data.model.Book
import com.fyooo.fybook.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: BookRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Book>>(UiState.Loading)
    val uiState: StateFlow<UiState<Book>> = _uiState

    fun getBookId(id: Long) {
        viewModelScope.launch {
            repository.getBookById(id)
                .catch { exception ->
                    _uiState.value = UiState.Error(exception.message ?: "Unknown error")
                }
                .collect { book ->
                    _uiState.value = UiState.Success(book)
                }
        }
    }

    fun insertCartBook(cartBook: CartBookEntity, snackbarHostState: SnackbarHostState) {
        viewModelScope.launch {
            repository.insertCartBook(cartBook)
                .catch { exception ->
                    _uiState.value = UiState.Error(exception.message ?: "Unknown error")
                }
                .collect { }
            snackbarHostState.showSnackbar("Added to cart!")
        }
    }
}