package com.fyooo.fybook.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fyooo.core.data.BookRepository
import com.fyooo.core.data.model.Book
import com.fyooo.fybook.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: BookRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Book>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Book>>> = _uiState

    fun fetchBooks() {
        viewModelScope.launch {
            repository.getAllBooks()
                .catch { exception ->
                    _uiState.value = UiState.Error(exception.message ?: "Unknown error")
                }
                .collect { books ->
                    _uiState.value = UiState.Success(books)
                }
        }
    }
}