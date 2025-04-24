package com.fyooo.fybook.ui.screen.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fyooo.fybook.data.BookRepository
import com.fyooo.fybook.data.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: BookRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<List<Book>>(emptyList())
    val uiState: StateFlow<List<Book>> = _uiState

    fun fetchBooks() {
        viewModelScope.launch {
            repository.getAllBooks()
                .catch { _uiState.value = emptyList() } // Handle errors
                .collect { books -> _uiState.value = books }
        }
    }
}