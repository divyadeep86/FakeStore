package com.my.fakestoredemo.categoriesAndProducts.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.my.fakestoredemo.categoriesAndProducts.domain.Product

@Composable
fun ProductList(productList: List<Product>, onClickProduct: (Int) -> Unit) {
    val imageHeight = LocalConfiguration.current.screenHeightDp * 0.2
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(items = productList, key = { it.id }) {
            ProductItem(product = it, imageHeight = imageHeight.dp, onClickProduct =onClickProduct)
        }

    }

}