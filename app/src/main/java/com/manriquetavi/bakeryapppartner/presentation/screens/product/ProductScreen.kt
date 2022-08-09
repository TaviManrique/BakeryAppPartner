package com.manriquetavi.bakeryapppartner.presentation.screens.product

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.manriquetavi.bakeryapppartner.domain.model.Category
import com.manriquetavi.bakeryapppartner.domain.model.Food
import com.manriquetavi.bakeryapppartner.domain.model.Response
import com.manriquetavi.bakeryapppartner.presentation.components.listcontent.CategoryItem
import com.manriquetavi.bakeryapppartner.presentation.components.listcontent.FoodItem
import com.manriquetavi.bakeryapppartner.presentation.components.progress.ProgressCircular
import com.manriquetavi.bakeryapppartner.presentation.components.textfield.SearchProductInputField
import com.manriquetavi.bakeryapppartner.ui.theme.SMALL_PADDING
import com.manriquetavi.bakeryapppartner.ui.theme.buttonBackgroundColor
import com.manriquetavi.bakeryapppartner.util.Util

@Composable
fun ProductScreen(
    paddingValues: PaddingValues,
    productViewModel: ProductViewModel = hiltViewModel()
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
        Spacer(modifier = Modifier.height(16.dp))
        //Row height 100.dp
        when(val allCategories = productViewModel.allCategories.value) {
            is Response.Loading -> CategoriesProgressBar()
            is Response.Success -> CategoriesContent(allCategories.data)
            is Response.Error -> Util.printError(allCategories.message)
        }
        Spacer(modifier = Modifier.height(16.dp))
        when(val searchedFoods = productViewModel.searchedFoods.value) {
            is Response.Loading -> SearchedFoodsProgressBar()
            is Response.Success -> SearchedFoodsContent(searchedFoods.data, productViewModel)
            is Response.Error -> Util.printError(searchedFoods.message)
        }
    }
}

@Composable
fun CategoriesContent(
    categories: List<Category>?
) {
    LazyRow(
        modifier = Modifier
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(SMALL_PADDING),
        horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ) {
        categories?.let {
            items(
                items = categories,
                key = { category ->
                    category.id!!
                }
            ) { category ->
                CategoryItem(category = category)
            }
        }
    }
}

@Composable
fun CategoriesProgressBar() {
    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
    ) {
        ProgressCircular()
    }
}

@Composable
fun SearchedFoodsProgressBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProgressCircular()
    }
}

@Composable
fun SearchedFoodsContent(foods: List<Food>?, productViewModel: ProductViewModel) {
    LazyColumn(
        modifier = Modifier
            .padding(top = SMALL_PADDING)
            .height(360.dp),
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ) {
        /*
        foods?.let {
            items(
                items = foods,
                key = { food ->
                    food.id!!
                }
            ) { food ->
                FoodItem(food = food, productViewModel = productViewModel)
            }
        }*/
        foods?.let { foods ->
            itemsIndexed(foods) { id, food ->
                FoodItem(id = id, food = food, productViewModel = productViewModel)
            }
        }
    }
}

@Preview
@Composable
fun ProductScreenPreview() {
    ProductScreen(paddingValues = PaddingValues(), productViewModel = hiltViewModel())
}