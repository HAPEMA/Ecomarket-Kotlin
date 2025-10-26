package com.example.ecomarket.ui.navigation

/**
 * Contiene las **rutas (routes)** utilizadas para la navegación entre pantallas.
 *
 * Cada ruta identifica una pantalla completa (destination) dentro de la app.
 * Este objeto centraliza las rutas para evitar errores de escritura
 * y mantener la navegación coherente en todo el proyecto.
 *
 * Ejemplo de uso:
 * ```kotlin
 * navController.navigate(Destinations.detail("5"))
 * ```
 */
object Destinations {

    /** Ruta de la pantalla principal (Home). */
    const val HOME = "home"

    /**
     * Ruta genérica para la pantalla de detalle de producto.
     * Contiene un parámetro de navegación dinámico `productId`.
     *
     * Ejemplo de ruta resultante: `detail/3`
     */
    const val DETAIL = "detail/{productId}"

    /**
     * Función auxiliar para construir una ruta válida hacia la pantalla de detalle.
     *
     * @param productId Identificador único del producto.
     * @return Cadena de navegación con el formato `detail/{productId}`.
     */
    fun detail(productId: String) = "detail/$productId"
}
