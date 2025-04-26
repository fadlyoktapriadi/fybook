package com.fyooo.fybook.ui.screen.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fyooo.fybook.data.BookRepository
import com.fyooo.fybook.data.api.response.ResultsItem
import com.fyooo.fybook.data.api.response.ResultsItemCity
import com.fyooo.fybook.data.api.response.ResultsItemProvince
import com.fyooo.fybook.data.local.entity.CartBookEntity
import com.fyooo.fybook.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CheckoutViewModel(private val repository: BookRepository) : ViewModel() {

    private val _provinces = MutableStateFlow<List<ResultsItemProvince>?>(null)
    val provinces: StateFlow<List<ResultsItemProvince>?> get() = _provinces

    private val _cities = MutableStateFlow<List<ResultsItemCity>>(emptyList())
    val cities: StateFlow<List<ResultsItemCity>> get() = _cities

    private val _shippingCost = MutableStateFlow<List<ResultsItem >>(emptyList())
    val shippingCost: StateFlow<List<ResultsItem>> get() = _shippingCost

    private val _cartBooks = MutableStateFlow<List<CartBookEntity>>(emptyList())
    val cartBooks: StateFlow<List<CartBookEntity>> get() = _cartBooks

    fun fetchProvinces() {
        viewModelScope.launch {
            repository.getProvinces().collect { response ->
                _provinces.value = response.rajaongkir.results
            }
        }
    }

    fun fetchCities(provinceId: String) {
        viewModelScope.launch {
            repository.getCities(provinceId).collect { response ->
                _cities.value = response.rajaongkir.results
            }
        }
    }

    fun getShippingCost(
        origin: String = "331",
        destination: String,
        weight: Int = 200,
        courier: String = "jne"
    ) {
        viewModelScope.launch {
            repository.getShippingCost(origin, destination, weight, courier).collect { response ->
                _shippingCost.value = response.rajaongkir.results
            }
        }
    }

    fun getAllCartBooks() {
        viewModelScope.launch {
            repository.getAllCartBooks().collect { books ->
                _cartBooks.value = books
            }
        }
    }
}