package com.example.ecomarket.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ecomarket.ui.screen.cart.CartScreen
import com.example.ecomarket.ui.screen.detail.ProductDetailScreen
import com.example.ecomarket.ui.screen.home.HomeScreen

/**
 * Controlador principal de navegación de la aplicación EcoMarket.
 *
 * Define el flujo entre pantallas y conecta las rutas declaradas en [Destinations].
 *
 * @param navController Controlador que gestiona el stack de pantallas.
 * @param cartVm ViewModel compartido del carrito de compras.
 */
@Composable
fun AppNavGraph(
    navController: NavHostController,
    cartVm: com.example.ecomarket.ui.screen.cart.CartViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.HOME
    ) {

        composable(Destinations.HOME) {
            HomeScreen(
                onProductClick = { id ->
                    navController.navigate(Destinations.detail(id))
                },
                onAddToCart = { p ->
                    cartVm.add(
                        productId = p.id,
                        nombre = p.nombre,
                        precioCLP = p.precioCLP
                    )
                },
                onCartClick = { navController.navigate("cart") }
            )
        }

        // 📄 Pantalla de detalle
        composable(Destinations.DETAIL) { backStack ->
            val id = backStack.arguments?.getString("productId") ?: return@composable
            ProductDetailScreen(
                productId = id,
                onAddToCart = { p ->
                    cartVm.add(
                        productId = p.id,
                        nombre = p.nombre,
                        precioCLP = p.precioCLP
                    )
                }
            )
        }

        // 🛒 Pantalla del carrito
        composable("cart") {
            CartScreen(cartVm = cartVm)
        }
    }
}
