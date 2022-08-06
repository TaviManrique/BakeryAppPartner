package com.manriquetavi.bakeryapppartner.presentation.screens.product

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manriquetavi.bakeryapppartner.presentation.components.textfield.SearchProductInputField
import com.manriquetavi.bakeryapppartner.ui.theme.buttonBackgroundColor

@Composable
fun ProductScreen(
    paddingValues: PaddingValues,
    productViewModel: ProductViewModel = ProductViewModel()
) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val searchQuery by productViewModel.searchQuery
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchProductInputField(
                modifier = Modifier.weight(0.9f),
                text = searchQuery,
                onTextChange = { productViewModel.updateSearchQuery(it) },
                onSearchClicked = { } ,
                focusManager = focusManager
            )
            IconButton(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.1f),
                onClick = {
                    Toast.makeText(context, "Add Icon", Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize(),
                    imageVector = Icons.Filled.Add,
                    tint = MaterialTheme.colors.buttonBackgroundColor,
                    contentDescription = "Remove Icon"
                )
            }
        }
    }
}

@Preview
@Composable
fun ProductScreenPreview() {
    ProductScreen(paddingValues = PaddingValues(), productViewModel = ProductViewModel())
}