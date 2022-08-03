package com.manriquetavi.bakeryapppartner.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object Main: Screen("main_screen")
    //BottomNav Screens
    object Home: Screen(
        route = "home_screen",
        title = "Home",
        icon = Icons.Outlined.Home
    )
    object Order: Screen(
        route = "order_screen",
        title = "Order",
        icon = Icons.Outlined.List
    )
    object Cart: Screen(
        route = "cart_screen",
        title = "Cart",
        icon = Icons.Outlined.ShoppingCart
    )
    object Profile: Screen(
        route = "profile_screen",
        title = "Profile",
        icon = Icons.Outlined.Person
    )
}