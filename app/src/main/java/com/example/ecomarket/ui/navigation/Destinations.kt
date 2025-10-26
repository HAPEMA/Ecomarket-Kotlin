package com.example.ecomarket.ui.navigation

object Destinations {
    const val HOME = "home"
    const val DETAIL = "detail/{productId}"
    fun detail(productId: String) = "detail/$productId"
}