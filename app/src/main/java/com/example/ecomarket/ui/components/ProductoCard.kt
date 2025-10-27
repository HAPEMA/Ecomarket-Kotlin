package com.example.ecomarket.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ecomarket.data.model.Producto
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

/**
 * Tarjeta reutilizable para mostrar un **producto** en la grilla/listado.
 *
 * Muestra:
 * - Imagen del producto (cargada desde URL con Coil).
 * - Tag opcional (p.ej. "Tortas Cuadradas") como AssistChip.
 * - Nombre del producto.
 * - Precio formateado en CLP.
 * - Acciones: "Ver Producto" y "Agregar al carrito".
 *
 * @param product Modelo de dominio con los datos a renderizar.
 * @param onClick Acción al pulsar **Ver Producto** (navegación a detalle).
 * @param onAddToCart Acción al pulsar **Agregar al carrito**.
 * @param modifier Modifier externo para tamaño/espaciado cuando la tarjeta se use dentro de listas o grids.
 */


@Composable
fun ProductCard(
    product: Producto,
    onClick: () -> Unit,
    onAddToCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    // bordes redondeados de la tarjeta
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        // Imagen del producto desde URL (Coil). Se limita altura y se redondean bordes.
        Column(Modifier.padding(12.dp)) {
            AsyncImage( model = product.imagenUrl, contentDescription = product.nombre, modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(12.dp))
            )

            // Si el producto tiene "tag", lo mostramos como chip descriptivo.
            product.tag?.let { tag ->
                AssistChip( onClick = {/*no navega por ahora*/}, label = { Text(tag) },
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // Nombre del producto (título destacado).
            Text(text = product.nombre, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)

            // Precio en CLP, sin decimales y con separadores de miles.
            val precioFormateado = NumberFormat.getCurrencyInstance(Locale("es", "CL")).apply {
                currency = Currency.getInstance("CLP")
                maximumFractionDigits = 0 }.format(product.precioCLP)

            Text(text = "Precio: $precioFormateado", style = MaterialTheme.typography.bodyMedium)

            // Acciones de la tarjeta: ver detalle y agregar al carrito.
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = onClick, modifier = Modifier.weight(1f) // ocupa todo el espacio disponible
                ) {
                    Text("Ver Producto")
                }
                OutlinedButton(onClick = onAddToCart) { Text("🧺") }
            }
        }
    }
}
