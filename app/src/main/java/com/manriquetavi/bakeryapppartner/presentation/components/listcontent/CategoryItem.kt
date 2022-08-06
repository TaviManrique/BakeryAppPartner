package com.manriquetavi.bakeryapppartner.presentation.components.listcontent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.manriquetavi.bakeryapppartner.R
import com.manriquetavi.bakeryapppartner.ui.theme.EXTRA_SMALL_PADDING
import com.manriquetavi.bakeryapppartner.ui.theme.PRODUCT_ITEM_HEIGHT

@Composable
fun CategoryItem(

) {
    Card(
        modifier = Modifier
            .size(80.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                modifier = Modifier
                    .padding(EXTRA_SMALL_PADDING)
                    .size(48.dp),
                color = Color.Transparent
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.White),
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data("https://firebasestorage.googleapis.com/v0/b/bakeryapp-d3dfa.appspot.com/o/categories_images%2Fcategory_cracker.png?alt=media&token=a6855c95-4b1d-4f29-b1be-b79200b87d90")
                        .build(),
                    placeholder = painterResource(id = R.drawable.ic_placeholder),
                    error = painterResource(id = R.drawable.ic_placeholder),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Product Image",
                )
            }
            Text(
                text = "Category",
                style = MaterialTheme.typography.caption,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CategoryItemPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(PRODUCT_ITEM_HEIGHT),
        contentAlignment = Alignment.Center
    ) {
        CategoryItem()
    }
}