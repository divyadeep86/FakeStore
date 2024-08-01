package com.my.fakestoredemo.common.appNavigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sbtakehomeassignment.common.uiComponents.LoadingContentWrapper
import com.my.fakestoredemo.categoriesAndProducts.domain.CategoryUiState
import com.my.fakestoredemo.categoriesAndProducts.ui.CategoriesScreen
import com.my.fakestoredemo.categoriesAndProducts.ui.CategoriesViewModel
import com.my.fakestoredemo.categoriesAndProducts.ui.ProductListViewModel
import com.my.fakestoredemo.productDetails.ProductDetailsScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    val actions = remember { AppNavigationActions(navController) }
    NavHost(
        navController = navController,
        startDestination = AppDestinations.MAIN_SCREEN,

        ) {

        composable(
            route = AppDestinations.MAIN_SCREEN
        ) {
            val categoriesViewModel: CategoriesViewModel = hiltViewModel()
            val categoriesViewState =
                categoriesViewModel.viewState.collectAsStateWithLifecycle().value
            val productListViewModel: ProductListViewModel = hiltViewModel()
            val productListViewState =
                productListViewModel.viewState.collectAsStateWithLifecycle().value
            LoadingContentWrapper(
                isLoading = categoriesViewState.isLoading,
                errorMessage = categoriesViewState.errorMessage,
                showDialog = categoriesViewState.errorMessage != null,
                dismissDialog = categoriesViewModel::clearAllMessages
            ) {
                categoriesViewState.dataState?.let {
                    if (it.categorList.isNotEmpty()) {
                        CategoriesScreen(categoryUiState = it,
                            productList = productListViewState.dataState!!,
                            onCategoryClick = {
                                productListViewModel.getProductByCategory(
                                    categoriesViewState.dataState!!.categorList[it]
                                )
                                categoriesViewModel.onClickCategory(it)
                            },
                            navToProductDetails = {
                                actions.navToProductDetailsScreen()
                            })
                    }

                }
            }


        }
        composable(route = AppDestinations.PRODUCT_DETAILS_SCREEN) {
           // ProductDetailsScreen()
        }
    }

}