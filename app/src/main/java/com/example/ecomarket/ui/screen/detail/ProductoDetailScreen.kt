package com.example.ecomarket.ui.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ecomarket.data.repo.ProductoRepository

/**
 * Pantalla de **detalle de producto**.
 *
 * Muestra la información completa de un producto seleccionado desde la lista:
 * - Imagen principal del producto.
 * - Nombre, categoría y precio.
 * - Botón para agregar el producto al carrito.
 *
 * Si el producto no existe (por ejemplo, si se accede con un ID inválido),
 * muestra un mensaje de error indicando que no fue encontrado.
 *
 * @param productId ID del producto seleccionado que llega desde la navegación.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(productId: String) {

    // Se busca el producto correspondiente al ID recibido.
    // "remember" evita recalcular el valor si el ID no cambia.
    val product = remember(productId) {
        ProductoRepository.products.firstOrNull { it.id == productId }
    }

    // Si no existe el producto, mostramos una pantalla de error simple.
    if (product == null) { Scaffold( topBar = { TopAppBar(title = { Text("Producto no encontrado") }) }
        ) { innerPadding -> Text(text = "Oops, no encontramos este producto.",  modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp))
        }
        return // terminamos la ejecución del composable
    }

    // Si el producto existe, mostramos su detalle completo.
    Scaffold(
        topBar = { TopAppBar(title = { Text(product.nombre) }) }
    ) { innerPadding -> Column(modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
        ) {
            // Imagen principal del producto (usa Coil para cargar desde URL).
            AsyncImage(model = product.imagenUrl, contentDescription = product.nombre, modifier = Modifier
                .fillMaxWidth().height(240.dp))
                Spacer(Modifier.height(16.dp))

            // Nombre del producto.
            Text(text = product.nombre, style = MaterialTheme.typography.headlineSmall)

            // Categoría (usa el tag si existe, o el nombre de categoría genérica).
            Text(text = "Categoría: ${product.tag ?: product.categoryId}", style = MaterialTheme.typography.bodyMedium)

            // Precio (sin formato monetario por ahora).
            Text(text = "Precio: ${product.precioCLP} CLP", style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(12.dp))

            // Botón de acción: agregar al carrito.
            Button(onClick = { /* TODO: Lógica para agregar al carrito */ }) {
                Text("Agregar al carrito")
            }
        }
    }
}
