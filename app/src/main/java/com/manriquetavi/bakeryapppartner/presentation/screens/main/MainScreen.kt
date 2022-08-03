package com.manriquetavi.bakeryapppartner.presentation.screens.main

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.manriquetavi.bakeryapppartner.navigation.Screen
import com.manriquetavi.bakeryapppartner.presentation.screens.report.HomeScreen
import com.manriquetavi.bakeryapppartner.presentation.screens.order.OrderScreen
import com.manriquetavi.bakeryapppartner.presentation.screens.product.ProductScreen
import com.manriquetavi.bakeryapppartner.presentation.screens.schedule.ScheduleScreen

@ExperimentalCoilApi
@Composable
fun MainScreen(
    screenNavController: NavHostController
) {

    val selectedItem = rememberSaveable { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomBar(selectedItem) {selectedItem.value = it}
        }
    ) { paddingValues ->
        when (selectedItem.value){
            0 -> HomeScreen(paddingValues)
            1 -> OrderScreen(paddingValues)
            2 -> ScheduleScreen(paddingValues)
            3 -> ProductScreen(paddingValues)
        }
    }
}

@Composable
fun BottomBar(
    selectedItem: MutableState<Int>,
    onSelectedItem: (Int) -> Unit,
) {
    BottomNavigation(
        modifier = Modifier
            .padding(start = 16.dp, bottom = 8.dp, end = 16.dp)
            .clip(RoundedCornerShape(30)),
        //backgroundColor = ShimmerMediumGray,
        elevation = 10.dp
    ) {
        val activity = (LocalContext.current as? Activity)
        BackHandler {
            if (selectedItem.value == 0) {
                activity?.finish()
            } else {
                onSelectedItem(0)
            }
        }

        val bottomScreens = listOf(
            Screen.Report,
            Screen.Order,
            Screen.Schedule,
            Screen.Product
        )

        bottomScreens.forEach { screen ->
            BottomNavigationItem(
                label = { Text(text = screen.title.toString()) },
                selected = selectedItem.value == bottomScreens.indexOf(screen),
                onClick = {
                    if (selectedItem.value != bottomScreens.indexOf(screen)) {
                        onSelectedItem(bottomScreens.indexOf(screen))
                    }
                },
                icon = { screen.icon?.let { Icon(imageVector = it, contentDescription = "Bottom Nav Icon") } },
                selectedContentColor = MaterialTheme.colors.primaryVariant,
                unselectedContentColor = Color.LightGray,
                enabled = true,
                alwaysShowLabel = false
            )
        }
    }
}

@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(screenNavController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomBar(
        selectedItem = remember { mutableStateOf(1) },
        onSelectedItem = {  }
    )
}