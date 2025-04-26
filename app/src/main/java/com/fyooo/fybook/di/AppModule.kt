package com.fyooo.fybook.di

import com.fyooo.fybook.ui.screen.home.HomeViewModel
import com.fyooo.fybook.ui.screen.cart.CartViewModel
import com.fyooo.fybook.ui.screen.checkout.CheckoutViewModel
import com.fyooo.fybook.ui.screen.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { CartViewModel(get()) }
    viewModel { CheckoutViewModel(get()) }
}