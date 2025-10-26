package com.example.ecomarket.ui.screen.home

import androidx.lifecycle.ViewModel
import com.example.ecomarket.data.repo.ProductoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel asociado a la pantalla [HomeScreen].
 *
 * Se encarga de:
 * - Mantener el estado actual de la categoría seleccionada.
 * - Proveer la lista de categorías disponibles.
 * - Proveer la lista de productos filtrados según la categoría activa.
 *
 * Implementa el patrón **MVVM**, separando la lógica de negocio de la interfaz.
 */

class HomeViewModel : ViewModel() {

    /**
     * Estado interno que almacena el ID de la categoría seleccionada.
     * Se inicializa con "all" (mostrar todos los productos).
     */
    private val _category = MutableStateFlow("all")

    /**
     * Versión inmutable del flujo de categoría expuesta a la UI.
     * La pantalla solo puede observar este valor, no modificarlo directamente.
     */
    val category = _category.asStateFlow()

    /**
     * Actualiza el valor de la categoría actual.
     *
     * @param catId ID de la nueva categoría seleccionada.
     */
    fun setCategory(catId: String) {
        _category.value = catId
    }

    /**
     * Devuelve la lista completa de categorías disponibles.
     * Los datos provienen del repositorio estático [ProductoRepository].
     *
     * @return Lista de categorías.
     */
    fun categories() = ProductoRepository.categories

    /**
     * Devuelve los productos filtrados según la categoría actual.
     * - Si la categoría seleccionada es `"all"`, se muestran todos los productos.
     * - En caso contrario, solo los productos cuyo `categoryId` coincide con la categoría activa.
     *
     * @return Lista de productos filtrados.
     */
    fun productsFiltered() =
        if (category.value == "all") {
            ProductoRepository.products
        } else {
            ProductoRepository.products.filter { it.categoryId == category.value }
        }
}
