package com.example.ecomarket.data.model

/** Representa un producto de EcoMarket. */
data class Producto(
    val id: String,            // ID único (String facilita navegación)
    val nombre: String,        // Nombre de catálogo
    val categoryId: String,    // FK a Category.id (evita duplicar texto)
    val precioCLP: Long,       // Precio en CLP (centavos si necesitas precisión)
    val imagenUrl: String?,    // URL de imagen (puede ser null)
    val tag: String? = null    // Etiqueta visible (“Tortas Cuadradas”, etc.)
)
