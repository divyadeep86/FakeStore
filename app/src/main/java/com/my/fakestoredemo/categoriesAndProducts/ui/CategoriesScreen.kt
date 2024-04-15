package com.my.fakestoredemo.categoriesAndProducts.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.my.fakestoredemo.categoriesAndProducts.domain.CategoryUiState
import com.my.fakestoredemo.categoriesAndProducts.domain.Product
import com.my.fakestoredemo.categoriesAndProducts.ui.components.ProductList
import com.my.fakestoredemo.common.uiComponents.AppScrollableTabRow

@Composable
fun CategoriesScreen(
    categoryUiState: CategoryUiState,
    productList: List<Product>,
    onCategoryClick: (Int) -> Unit,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AppScrollableTabRow(
            titles = categoryUiState.categorList,
            selectedTabIndex = categoryUiState.selectedCategoryIndex,
            onTabSelected = onCategoryClick
        )
        LaunchedEffect(key1 = categoryUiState.categorList.isNotEmpty()) {
            onCategoryClick(categoryUiState.selectedCategoryIndex)
        }
        ProductList(productList = productList, onClickProduct = onClick)
    }
}


