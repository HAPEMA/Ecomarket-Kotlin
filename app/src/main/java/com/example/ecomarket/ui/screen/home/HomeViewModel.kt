package com.example.ecomarket.ui.screen.home

import androidx.lifecycle.ViewModel
import com.example.ecomarket.data.repo.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _category = MutableStateFlow("all")
    val category = _category.asStateFlow()

    fun setCategory(catId: String) { _category.value = catId }

    fun categories() = ProductRepository.categories

    fun productsFiltered() =
        if (category.value == "all") ProductRepository.products
        else ProductRepository.products.filter { it.category == category.value }
}
