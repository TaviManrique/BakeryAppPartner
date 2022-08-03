package com.manriquetavi.bakeryapppartner.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.manriquetavi.bakeryapppartner.presentation.screens.cart.CartScreen
import com.manriquetavi.bakeryapppartner.presentation.screens.home.HomeScreen
import com.manriquetavi.bakeryapppartner.presentation.screens.main.MainScreen
import com.manriquetavi.bakeryapppartner.presentation.screens.order.OrderScreen
import com.manriquetavi.bakeryapppartner.presentation.screens.profile.ProfileScreen

@Composable
fun SetupNavGraph(screenNavController: NavHostController) {
    NavHost(
        navController = screenNavController,
        startDestination = Screen.Main.route
    ) {
        composable(route = Screen.Main.route) {
            MainScreen()
        }
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
        composable(route = Screen.Cart.route) {
            CartScreen()
        }
        composable(route = Screen.Order.route) {
            OrderScreen()
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen()
        }
    }
}