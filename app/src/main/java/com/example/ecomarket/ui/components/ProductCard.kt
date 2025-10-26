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
import com.example.ecomarket.data.model.Product
import java.text.NumberFormat
import java.util.*

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit,
    onAddToCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Column(Modifier.padding(12.dp)) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            product.tag?.let {
                AssistChip(onClick = {}, label = { Text(it) }, modifier = Modifier.padding(top = 8.dp))
            }

            Text(product.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
            Text(
                "Precio: " + NumberFormat.getCurrencyInstance(Locale("es", "CL")).apply {
                    currency = Currency.getInstance("CLP")
                    maximumFractionDigits = 0
                }.format(product.price),
                style = MaterialTheme.typography.bodyMedium
            )

            Row(Modifier.fillMaxWidth().padding(top = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = onClick, modifier = Modifier.weight(1f)) { Text("Ver Producto") }
                OutlinedButton(onClick = onAddToCart) { Text("🧺") }
            }
        }
    }
}
