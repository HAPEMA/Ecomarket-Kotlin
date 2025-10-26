package com.example.ecomarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.ecomarket.ui.navigation.AppNavGraph
import com.example.ecomarket.ui.theme.EcoMarketTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcoMarketTheme {
                val navController = rememberNavController()
                AppNavGraph(navController)
            }
        }
    }
}
