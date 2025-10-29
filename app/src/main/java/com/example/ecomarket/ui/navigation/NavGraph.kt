package com.example.ecomarket.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ecomarket.ui.screen.cart.CartScreen
import com.example.ecomarket.ui.screen.detail.ProductDetailScreen
import com.example.ecomarket.ui.screen.form.ProductFormScreen
import com.example.ecomarket.ui.screen.home.HomeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    cartVm: com.example.ecomarket.ui.screen.cart.CartViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.HOME
    ) {
        // 🏠 Home
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
                onCartClick = { navController.navigate("cart") },
                onAddProductClick = { navController.navigate("add_product") }
            )
        }

        // 📄 Detalle de producto
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

        // 🛒 Carrito
        composable("cart") {
            CartScreen(cartVm = cartVm)
        }

        // ➕ Formulario: agregar producto
        composable("add_product") {
            ProductFormScreen(
                onSaved = { navController.popBackStack() },
                onCancel = { navController.popBackStack() }
            )
        }
    }
}
