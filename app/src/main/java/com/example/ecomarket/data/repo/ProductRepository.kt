package com.example.ecomarket.data.repo

import com.example.ecomarket.data.model.Category
import com.example.ecomarket.data.model.Product

object ProductRepository {
    val categories = listOf(
        Category("all", "Todos"),
        Category("cuadradas", "Tortas Cuadradas"),
        Category("circulares", "Tortas Circulares"),
        Category("sin_azucar", "Productos Sin Azúcar"),
        Category("tradicional", "Pastelería Tradicional"),
        Category("sin_gluten", "Productos Sin Gluten"),
        Category("veganos", "Productos Veganos"),
        Category("especiales", "Tortas Especiales"),
    )

    val products = listOf(
        Product("1","Torta Cuadrada de Chocolate","cuadradas",45000,
            "https://picsum.photos/seed/choco/800/600","Tortas Cuadradas"),
        Product("2","Torta Cuadrada de Frutas","cuadradas",50000,
            "https://picsum.photos/seed/frutas/800/600","Tortas Cuadradas"),
        Product("3","Torta Circular de Vainilla","circulares",40000,
            "https://picsum.photos/seed/vainilla/800/600","Tortas Circulares"),
        Product("4","Torta Circular de Manjar","circulares",42000,
            "https://picsum.photos/seed/manjar/800/600","Tortas Circulares"),
    )
}
