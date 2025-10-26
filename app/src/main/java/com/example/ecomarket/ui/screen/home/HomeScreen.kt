package com.example.ecomarket.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecomarket.ui.components.EcoTopBar
import com.example.ecomarket.ui.components.ProductCard

/**
 * Pantalla principal de la aplicación (**HomeScreen**).
 *
 * Esta es la primera pantalla que ve el usuario al abrir la app.
 * Contiene:
 * - La barra superior ([EcoTopBar]) con el menú de categorías y acceso al carrito.
 * - Una sección de presentación (“hero”) con información de la tienda.
 * - Un listado de productos mostrado en formato de grilla.
 *
 * @param onProductClick Acción que se ejecuta al hacer clic en un producto
 *                       (por ejemplo, navegar al detalle).
 * @param vm ViewModel asociado ([HomeViewModel]) que maneja el estado
 *           de categorías y productos.
 */

// ViewModel inyectado automáticamente
@Composable
fun HomeScreen(
    onProductClick: (String) -> Unit,
    vm: HomeViewModel = viewModel()
) {
    // Estado actual: categoría seleccionada.
    val selectedCat by vm.category.collectAsState()

    // Listas de categorías y productos (filtrados según la categoría seleccionada).
    val categories = vm.categories()
    val products = vm.productsFiltered()

    // --- Barra superior (AppBar con menú y carrito) ---
    Scaffold(
        topBar = {
            EcoTopBar(
                categories = categories,              // Lista de categorías visibles
                selectedCategory = selectedCat,       // Categoría actual
                onCategorySelected = { vm.setCategory(it) }, // Actualiza categoría al seleccionar
                onCartClick = { /* TODO: navegación al carrito */ }
            )
        }
    ) {
        // Contenido principal del cuerpo (body) de la pantalla
        innerPadding -> Column(Modifier.padding(innerPadding)) {

            // --- Sección de presentación (Hero Section) ---
            Surface(
                color = MaterialTheme.colorScheme.secondaryContainer, modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("Buscamos ofrecer una experiencia de compra moderna.",style = MaterialTheme.typography.headlineSmall
                    )
                    Text("Repostería de alta calidad para todas las ocasiones.", style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = { /* TODO: navegación a la tienda completa */ }) {
                        Text("Ver Tienda")
                    }
                }
            }
            Spacer(Modifier.height(12.dp))

            // --- Título de la sección de productos ---
            Text("Nuestros Productos",style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(Modifier.height(8.dp))

            // --- Grilla de productos ---
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 260.dp),   // Tamaño adaptable a pantalla
                contentPadding = PaddingValues(16.dp),            // Márgenes de la grilla
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Renderiza cada producto usando ProductCard
                items(products, key = { it.id }) { product ->
                    ProductCard(
                        product = product,
                        onClick = { onProductClick(product.id) }, // Va al detalle
                        onAddToCart = { /* TODO: agregar al carrito */ },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
