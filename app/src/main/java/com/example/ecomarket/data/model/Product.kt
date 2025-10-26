package com.example.ecomarket.data.model

data class Product(
    val id: String,
    val name: String,
    val category: String,
    val price: Int,           // CLP
    val imageUrl: String,
    val tag: String? = null   // p.ej. "Tortas Cuadradas"
)