package com.example.ecomarket.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecomarket.data.local.LocalProductStore
import com.example.ecomarket.data.model.Producto
import com.example.ecomarket.ui.components.EcoTopBar
import com.example.ecomarket.ui.components.ProductCard

/**
 * Pantalla principal de la aplicación (**HomeScreen**).
 *
 * - TopBar con categorías y acceso al carrito.
 * - Sección “hero”.
 * - Grilla de productos (repositorio + agregados locales).
 * - FAB para ir al formulario de “Agregar producto”.
 */
@Composable
fun HomeScreen(
    onProductClick: (String) -> Unit,
    onAddToCart: (Producto) -> Unit,
    onCartClick: () -> Unit,
    vm: HomeViewModel = viewModel(),
    onAddProductClick: () -> Unit = {} // Navega a "add_product"
) {
    // Estado actual de categoría
    val selectedCat by vm.category.collectAsState()
    val categories = vm.categories()

    // Productos agregados por el usuario (persistidos en SharedPreferences)
    val ctx = LocalContext.current
    val userProducts = remember { mutableStateListOf<Producto>() }

    // Carga inicial desde local storage
    LaunchedEffect(Unit) {
        userProducts.clear()
        userProducts.addAll(LocalProductStore.load(ctx))
    }

    // Merge de productos: repositorio + locales (filtrados por categoría)
    val products = remember(selectedCat, userProducts) {
        val base = vm.productsFiltered()
        val locals = if (selectedCat == "all") userProducts
        else userProducts.filter { it.categoryId == selectedCat }
        base + locals
    }

    Scaffold(
        topBar = {
            EcoTopBar(
                categories = categories,
                selectedCategory = selectedCat,
                onCategorySelected = { vm.setCategory(it) },
                onCartClick = onCartClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddProductClick) {
                Icon(Icons.Default.Add, contentDescription = "Agregar producto")
            }
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {

            // Hero simple
            Surface(
                color = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        "Buscamos ofrecer una experiencia de compra moderna.",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        "Repostería de alta calidad para todas las ocasiones.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = { /* TODO: navegar a tienda completa */ }) {
                        Text("Ver Tienda")
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            Text(
                "Nuestros Productos",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(8.dp))

            // Grilla de productos
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 260.dp),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(products, key = { it.id }) { product ->
                    ProductCard(
                        product = product,
                        onClick = { onProductClick(product.id) },
                        onAddToCart = { onAddToCart(product) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
