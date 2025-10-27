package com.example.ecomarket.ui.screen.cart

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Pantalla del **carrito de compras**.
 *
 * Muestra la lista de items agregados y el **total**. Si no hay productos, muestra
 * un estado vacío simple. El ViewModel expone el estado vía StateFlow.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(cartVm: CartViewModel) {
    val items by cartVm.items.collectAsState()
    val total = cartVm.totalCLP()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Carrito (${items.sumOf { it.qty }})") })
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp)
        ) {
            if (items.isEmpty()) {
                Text("Tu carrito está vacío por ahora")
            } else {
                items.forEach { it ->
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("${it.nombre} x${it.qty}")
                        Text("${it.precioCLP * it.qty} CLP")
                    }
                    Spacer(Modifier.height(8.dp))
                }

                Divider()
                Spacer(Modifier.height(8.dp))

                Text(
                    "Total: $total CLP",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = { /* TODO: flujo de pago */ },
                    enabled = items.isNotEmpty()
                ) {
                    Text("Pagar")
                }
            }
        }
    }
}
