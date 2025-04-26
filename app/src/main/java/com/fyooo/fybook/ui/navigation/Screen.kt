package com.fyooo.fybook.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object DetailBook : Screen("home/{id}") {
        fun createRoute(id: Long) = "home/$id"
    }
    data object Cart : Screen("cart")
    data object Checkout: Screen("checkout")

}