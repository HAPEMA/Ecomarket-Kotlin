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

@Composable
fun HomeScreen(
    onProductClick: (String) -> Unit,
    vm: HomeViewModel = viewModel()
) {
    val selectedCat by vm.category.collectAsState()
    val categories = vm.categories()
    val products = vm.productsFiltered()

    Scaffold(
        topBar = {
            EcoTopBar(
                categories = categories,
                selectedCategory = selectedCat,
                onCategorySelected = { vm.setCategory(it) },
                onCartClick = { /* TODO: nav a carrito */ }
            )
        }
    ) { inner ->
        Column(Modifier.padding(inner)) {

            // “Hero” muy simple (puedes reemplazar por una imagen grande)
            Surface(color = MaterialTheme.colorScheme.secondaryContainer, modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text("Buscamos ofrecer una experiencia de compra moderna.",
                        style = MaterialTheme.typography.headlineSmall)
                    Text("Repostería de alta calidad para todas las ocasiones.",
                        style = MaterialTheme.typography.bodyMedium)
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = { /* TODO: navegar a productos */ }) { Text("Ver Tienda") }
                }
            }

            Spacer(Modifier.height(12.dp))

            Text("Nuestros Productos", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(horizontal = 16.dp))

            Spacer(Modifier.height(8.dp))

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 260.dp),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(products, key = { it.id }) { p ->
                    ProductCard(
                        product = p,
                        onClick = { onProductClick(p.id) },
                        onAddToCart = { /* TODO: add al carrito */ },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
