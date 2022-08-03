package com.manriquetavi.bakeryapppartner.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object Main: Screen("main_screen")
    //BottomNav Screens
    object Report: Screen(
        route = "report_screen",
        title = "Report",
        icon = Icons.Outlined.BarChart
    )
    object Order: Screen(
        route = "order_screen",
        title = "Order",
        icon = Icons.Outlined.List
    )
    object Schedule: Screen(
        route = "schedule_screen",
        title = "Schedule",
        icon = Icons.Outlined.ManageHistory
    )
    object Product: Screen(
        route = "product_screen",
        title = "Product",
        icon = Icons.Outlined.RestaurantMenu
    )
}