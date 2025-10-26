package com.example.ecomarket.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.ecomarket.data.model.Category

@Composable
fun EcoTopBar(
    categories: List<Category>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    onCartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    @OptIn(ExperimentalMaterial3Api::class)
    TopAppBar(
        title = { Text("Pastelería Mil Sabores") },
        actions = {
            // Botón de categorías (dropdown)
            TextButton(onClick = { expanded = true }) { Text("Productos") }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                categories.forEach { cat ->
                    DropdownMenuItem(
                        text = { Text(cat.name) },
                        onClick = {
                            onCategorySelected(cat.id)
                            expanded = false
                        }
                    )
                }
            }

            // Carrito (placeholder)
            TextButton(onClick = onCartClick) { Text("🛒") }
        },
        modifier = modifier
    )
}
