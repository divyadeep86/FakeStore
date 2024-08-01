package com.my.fakestoredemo.categoriesAndProducts.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.my.fakestoredemo.categoriesAndProducts.domain.CategoryUiState
import com.my.fakestoredemo.categoriesAndProducts.domain.Product
import com.my.fakestoredemo.categoriesAndProducts.ui.components.ProductList
import com.my.fakestoredemo.common.uiComponents.AppScrollableTabRow
import com.my.fakestoredemo.common.utils.TestTags
import com.my.fakestoredemo.productDetails.ProductDetailsScreen

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun CategoriesScreen(
    categoryUiState: CategoryUiState,
    productList: List<Product>,
    onCategoryClick: (Int) -> Unit,
    navToProductDetails: (Int) -> Unit
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(navigator = navigator, listPane = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .testTag(TestTags.HomeScreen),
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
            ProductList(productList = productList, onClickProduct = {
                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail,it)
            })
        }
    }, detailPane = {
        val id = navigator.currentDestination?.content as? Int
id?.let {
    ProductDetailsScreen(id)
}
    })


}


