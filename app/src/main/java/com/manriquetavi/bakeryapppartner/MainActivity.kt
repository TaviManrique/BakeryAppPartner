package com.manriquetavi.bakeryapppartner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.manriquetavi.bakeryapppartner.navigation.SetupNavGraph
import com.manriquetavi.bakeryapppartner.ui.theme.BakeryAppPartnerTheme

@ExperimentalCoilApi
class MainActivity : ComponentActivity() {

    private lateinit var screenNavController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BakeryAppPartnerTheme {
                // A surface container using the 'background' color from the theme
                screenNavController =  rememberNavController()
                SetupNavGraph(screenNavController = screenNavController)
            }
        }
    }
}