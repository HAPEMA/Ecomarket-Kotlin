package com.example.ecomarket.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ecomarket.ui.screen.detail.ProductDetailScreen
import com.example.ecomarket.ui.screen.home.HomeScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Destinations.HOME) {
        composable(Destinations.HOME) {
            HomeScreen(
                onProductClick = { id -> navController.navigate(Destinations.detail(id)) }
            )
        }
        composable(Destinations.DETAIL) { backStack ->
            val id = backStack.arguments?.getString("productId") ?: return@composable
            ProductDetailScreen(productId = id)
        }
    }
}
