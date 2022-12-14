package com.manriquetavi.bakeryapppartner.presentation.components.listcontent

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.manriquetavi.bakeryapppartner.R
import com.manriquetavi.bakeryapppartner.domain.model.Food
import com.manriquetavi.bakeryapppartner.domain.model.Response
import com.manriquetavi.bakeryapppartner.ui.theme.*
import com.manriquetavi.bakeryapppartner.presentation.components.Switch
import com.manriquetavi.bakeryapppartner.presentation.components.progress.ProgressCircular
import com.manriquetavi.bakeryapppartner.presentation.screens.product.ProductViewModel
import com.manriquetavi.bakeryapppartner.util.Util

@Composable
fun FoodItem(
    id: Int,
    food: Food,
    productViewModel: ProductViewModel
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(PRODUCT_ITEM_HEIGHT)
            .padding(EXTRA_SMALL_PADDING)
            .clickable {
                Toast
                    .makeText(context, "Card", Toast.LENGTH_SHORT)
                    .show()
            },
        shape = RoundedCornerShape(SMALL_PADDING),
        backgroundColor = Purple200
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .padding(SMALL_PADDING)
                    .weight(0.5f),
                elevation = 4.dp,
                color = Color.Transparent
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White),
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(food.image)
                        .build(),
                    placeholder = painterResource(id = R.drawable.ic_placeholder),
                    error = painterResource(id = R.drawable.ic_placeholder),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Product Image",
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
                    .padding(EXTRA_SMALL_PADDING),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = food.name.toString(),
                        style = MaterialTheme.typography.h6,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = food.category.toString(),
                        style = MaterialTheme.typography.caption,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = food.price.toString(),
                        style = MaterialTheme.typography.caption,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    when(val changeOnStatus = productViewModel.changeOnStockState.value) {
                        is Response.Loading -> CircularProgressIndicator()
                        is Response.Success -> Switch(
                            modifier = Modifier
                                .padding(horizontal = 16.dp),
                            width = 30.dp,
                            height = 15.dp,
                            switchState = food.onStock
                        ) {
                            productViewModel.changeOnStockStatus(
                                foodId = food.id!!,
                                onStock = food.onStock
                            )
                        }
                        is Response.Error -> Util.printError(changeOnStatus.message)
                    }

                    IconButton(
                        onClick = {
                            Toast.makeText(context, "Edit Icon", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            tint = MaterialTheme.colors.buttonBackgroundColor,
                            contentDescription = "Edit Icon"
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FoodItemPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(PRODUCT_ITEM_HEIGHT),
        contentAlignment = Alignment.Center
    ) {
        FoodItem(
            id = 1,
            food = Food(),
            productViewModel = hiltViewModel()
        )
    }
}