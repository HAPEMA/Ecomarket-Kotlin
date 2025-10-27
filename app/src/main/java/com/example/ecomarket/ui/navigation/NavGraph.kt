package com.example.ecomarket.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ecomarket.ui.screen.detail.ProductDetailScreen
import com.example.ecomarket.ui.screen.home.HomeScreen

/**
 * Controlador principal de navegación de la aplicación EcoMarket.
 *
 * Define el flujo entre las pantallas principales usando **Jetpack Navigation Compose**.
 * En este caso, la aplicación posee dos pantallas:
 * - [HomeScreen]: pantalla principal donde se listan los productos.
 * - [ProductDetailScreen]: pantalla de detalle de un producto.
 *
 * @param navController Controlador de navegación que gestiona el stack de pantallas.
 */

@Composable
fun AppNavGraph(navController: NavHostController) {
    // NavHost actúa como contenedor que muestra la pantalla correspondiente
    // según la "route" actual definida en Destinations.kt
    NavHost(navController, startDestination = Destinations.HOME) // pantalla inicial de la app
    {
        // Pantalla principal (home)
        composable(Destinations.HOME) {
            HomeScreen(
                // Cuando se selecciona un producto, navega al detalle (Fuera de uso aun)
                onProductClick = { id -> navController.navigate(Destinations.detail(id))
                }
            )
        }
        // Pantalla de detalle de producto
        composable(Destinations.DETAIL) { backStack ->
            // Se extrae el argumento "productId" desde la ruta
            val id = backStack.arguments?.getString("productId")
            // si no existe, cancela la carga
                ?: return@composable

            ProductDetailScreen(productId = id)
        }
    }
}
