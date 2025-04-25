package com.fyooo.fybook.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose.FybookTheme
import com.fyooo.fybook.ui.navigation.Screen
import com.fyooo.fybook.ui.screen.Home.HomeScreen
import com.fyooo.fybook.ui.screen.cart.CartScreen
import com.fyooo.fybook.ui.screen.checkout.CheckoutScreen
import com.fyooo.fybook.ui.screen.detail.DetailScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FyBookApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.DetailBook.createRoute(id))
                    },
                    navigateToCart = {
                        navController.navigate(Screen.Cart.route)
                    }
                )
            }

            composable(
                route = Screen.DetailBook.route,
                arguments = listOf(navArgument("id") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("id") ?: -1L
                DetailScreen(
                    id = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToCart = {
                        navController.navigate(Screen.Cart.route)
                    }
                )
            }

            composable(Screen.Cart.route) {
                CartScreen(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToCheckout = {
                        navController.navigate(Screen.Checkout.route)
                    }
                )
            }

            composable(Screen.Checkout.route) {
                CheckoutScreen(
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}
