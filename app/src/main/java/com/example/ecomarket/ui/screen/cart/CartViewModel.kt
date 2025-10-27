package com.example.ecomarket.ui.screen.cart

import androidx.lifecycle.ViewModel
import com.example.ecomarket.data.model.CartItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CartViewModel : ViewModel() {

    private val _items = MutableStateFlow<List<CartItem>>(emptyList())
    val items = _items.asStateFlow()

    fun add(productId: String, nombre: String, precioCLP: Long) {
        val current = _items.value.toMutableList()
        val idx = current.indexOfFirst { it.productId == productId }
        if (idx >= 0) {
            val it = current[idx]
            current[idx] = it.copy(qty = it.qty + 1)
        } else {
            current += CartItem(productId, nombre, precioCLP, qty = 1)
        }
        _items.value = current
    }

    fun removeOne(productId: String) {
        val current = _items.value.toMutableList()
        val idx = current.indexOfFirst { it.productId == productId }
        if (idx >= 0) {
            val it = current[idx]
            if (it.qty > 1) current[idx] = it.copy(qty = it.qty - 1)
            else current.removeAt(idx)
            _items.value = current
        }
    }

    fun clear() { _items.value = emptyList() }

    fun totalCLP(): Long = _items.value.sumOf { it.precioCLP * it.qty }
}
