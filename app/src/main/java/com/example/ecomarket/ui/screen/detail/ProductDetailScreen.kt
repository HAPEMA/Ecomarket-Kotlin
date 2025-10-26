package com.example.ecomarket.ui.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ecomarket.data.repo.ProductRepository


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(productId: String) {
    val product = remember(productId) {
        com.example.ecomarket.data.repo.ProductRepository.products
            .firstOrNull { it.id == productId }
    }

    // Si no existe, mostramos un mensaje en vez de crashear
    if (product == null) {
        Scaffold(topBar = { TopAppBar(title = { Text("Producto no encontrado") }) }) { inner ->
            Text(
                "Oops, no encontramos este producto.",
                modifier = Modifier.padding(inner).padding(16.dp)
            )
        }
        return
    }

    Scaffold(topBar = { TopAppBar(title = { Text(product.name) }) }) { inner ->
        Column(Modifier.padding(inner).padding(16.dp)) {
            coil.compose.AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier.fillMaxWidth().height(240.dp)
            )
            Spacer(Modifier.height(16.dp))
            Text(product.name, style = MaterialTheme.typography.headlineSmall)
            Text("Categoría: ${product.tag ?: product.category}")
            Text("Precio: ${product.price} CLP")
            Spacer(Modifier.height(12.dp))
            Button(onClick = { /* TODO: add to cart */ }) { Text("Agregar al carrito") }
        }
    }
}
