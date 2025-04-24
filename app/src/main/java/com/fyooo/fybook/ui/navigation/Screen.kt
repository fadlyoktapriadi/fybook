package com.fyooo.fybook.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object DetailBook : Screen("home/{id}") {
        fun createRoute(id: Long) = "home/$id"
    }
    object Cart : Screen("cart")
    object Checkout: Screen("checkout")

}