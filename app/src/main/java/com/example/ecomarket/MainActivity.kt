package com.example.ecomarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.ecomarket.ui.navigation.AppNavGraph
import com.example.ecomarket.ui.theme.EcoMarketTheme
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Actividad principal de la aplicación EcoMarket.
 *
 * - Aplica el tema Material 3.
 * - Inicializa la navegación Jetpack Compose.
 * - Crea un [CartViewModel] compartido entre pantallas.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcoMarketTheme {
                val nav = rememberNavController()
                val cartVm: com.example.ecomarket.ui.screen.cart.CartViewModel = viewModel()
                AppNavGraph(navController = nav, cartVm = cartVm)
            }
        }
    }
}

