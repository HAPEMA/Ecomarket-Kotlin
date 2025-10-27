package com.example.ecomarket.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.ecomarket.data.model.Categoria

/**
 * Barra superior de la aplicación (**TopAppBar**) que muestra:
 * - El nombre de la tienda ("Pastelería Mil Sabores")
 * - Un menú desplegable de **categorías de productos**
 * - Un botón para acceder al **carrito de compras**
 *
 * Esta barra se utiliza como componente global en las pantallas principales del e-commerce.
 *
 * @param categories Lista de categorías disponibles en la tienda.
 * @param selectedCategory ID de la categoría actualmente seleccionada.
 * @param onCategorySelected Acción que se ejecuta al seleccionar una categoría del menú.
 * @param onCartClick Acción al pulsar el botón del carrito 🛒.
 * @param modifier Permite aplicar modificaciones externas al componente (margen, tamaño, etc.).
 */
@Composable
fun EcoTopBar(
    categories: List<Categoria>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    onCartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Estado local que controla si el menú de categorías está expandido o no.
    var expanded by remember { mutableStateOf(false) }

    @OptIn(ExperimentalMaterial3Api::class)
    TopAppBar(title = { Text("Pastelería Mil Sabores") }, // Título principal de la aplicación

        // --- Menú desplegable de categorías ---
        actions = { TextButton(onClick = { expanded = true }) {Text("Productos") }

            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }
            ) {
                categories.forEach { cat ->
                    DropdownMenuItem(
                        text = { Text(cat.nombre) },
                        onClick = {
                            onCategorySelected(cat.id) // Notifica la categoría seleccionada
                            expanded = false            // Cierra el menú
                        }
                    )
                }
            }

            // --- Botón del carrito de compras ---
            TextButton(onClick = onCartClick) {Text("🛒") }
        },
        modifier = modifier
    )
}
