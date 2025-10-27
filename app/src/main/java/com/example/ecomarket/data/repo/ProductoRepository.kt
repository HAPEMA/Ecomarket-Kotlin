package com.example.ecomarket.data.repo

import com.example.ecomarket.data.model.Categoria
import com.example.ecomarket.data.model.Producto

object ProductoRepository {

    /** Menú de categorías mostrado en la TopBar. */
    val categories = listOf(
        Categoria("all", "Todos"),
        Categoria("cuadradas", "Tortas Cuadradas"),
        Categoria("circulares", "Tortas Circulares"),
        Categoria("individuales", "Postres Individuales"),
        Categoria("tradicional", "Pastelería Tradicional"),
        Categoria("veganos", "Productos Veganos"),
        Categoria("sin_azucar", "Productos Sin Azúcar"),
        Categoria("sin_gluten", "Productos Sin Gluten"),
        Categoria("especiales", "Tortas Especiales")
    )

    /** Catálogo inicial mock. */
    val products = listOf(
        Producto(
            id = "1",
            nombre = "Torta Cuadrada de Chocolate",
            categoryId = "cuadradas",
            precioCLP = 45_000,
            imagenUrl = "https://patty.pe/wp-content/uploads/2024/05/TORTA-CUADRADA-2.png",
            tag = "Tortas Cuadradas"
        )
    )
}
