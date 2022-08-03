package com.manriquetavi.bakeryapppartner.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.manriquetavi.bakeryapppartner.presentation.screens.main.MainScreen

@ExperimentalCoilApi
@Composable
fun SetupNavGraph(screenNavController: NavHostController) {
    NavHost(
        navController = screenNavController,
        startDestination = Screen.Main.route
    ) {
        composable(route = Screen.Main.route) {
            MainScreen(screenNavController)
        }
    }
}