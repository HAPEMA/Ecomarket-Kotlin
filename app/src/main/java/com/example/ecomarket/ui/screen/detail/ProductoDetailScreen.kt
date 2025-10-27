package com.example.ecomarket.ui.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ecomarket.data.model.Producto
import com.example.ecomarket.data.repo.ProductoRepository
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

/**
 * Pantalla de **detalle de producto**.
 *
 * Muestra imagen, nombre, categoría y precio; además permite agregar el producto al carrito.
 * Si el ID no existe, entrega una pantalla de “producto no encontrado”.
 *
 * @param productId     ID del producto (desde la navegación).
 * @param onAddToCart   Callback al presionar “Agregar al carrito”.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: String,
    onAddToCart: (Producto) -> Unit
) {
    // Buscar producto por ID (memoizado por clave productId)
    val product = remember(productId) {
        ProductoRepository.products.firstOrNull { it.id == productId }
    }

    // Estado: producto inexistente
    if (product == null) {
        Scaffold(topBar = { TopAppBar(title = { Text("Producto no encontrado") }) }) { inner ->
            Text(
                "Oops, no encontramos este producto.",
                modifier = Modifier.padding(inner).padding(16.dp)
            )
        }
        return
    }

    // Formateo CLP (sin decimales)
    val precioFormateado = remember(product.precioCLP) {
        NumberFormat.getCurrencyInstance(Locale("es", "CL")).apply {
            currency = Currency.getInstance("CLP")
            maximumFractionDigits = 0
        }.format(product.precioCLP)
    }

    // Detalle del producto
    Scaffold(topBar = { TopAppBar(title = { Text(product.nombre) }) }) { inner ->
        Column(
            Modifier
                .padding(inner)
                .padding(16.dp)
        ) {
            AsyncImage(
                model = product.imagenUrl,
                contentDescription = product.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            )

            Spacer(Modifier.height(16.dp))

            Text(product.nombre, style = MaterialTheme.typography.headlineSmall)
            Text("Categoría: ${product.tag ?: product.categoryId}", style = MaterialTheme.typography.bodyMedium)
            Text("Precio: $precioFormateado", style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(12.dp))

            Button(onClick = { onAddToCart(product) }) {
                Text("Agregar al carrito")
            }
        }
    }
}
