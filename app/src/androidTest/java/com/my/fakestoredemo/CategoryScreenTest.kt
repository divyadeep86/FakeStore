package com.my.fakestoredemo

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.sbtakehomeassignment.common.uiComponents.LoadingContentWrapper
import com.my.fakestoredemo.categoriesAndProducts.domain.CategoryUiState
import com.my.fakestoredemo.categoriesAndProducts.ui.CategoriesScreen
import com.my.fakestoredemo.common.state.ViewState
import com.my.fakestoredemo.common.utils.TestTags
import org.junit.Rule
import org.junit.Test

class CategoryScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun test_CategoryScreen_LoadingStateVisibilty_TrueExpected() {

        composeTestRule.setContent {
            val categoryViewState = ViewState(isLoading = true, dataState = null)
            LoadingContentWrapper(isLoading = categoryViewState.isLoading, errorMessage = null) {
                categoryViewState.dataState?.let {
                    CategoriesScreen(categoryUiState = CategoryUiState(),
                        productList = listOf(),
                        navToProductDetails = {},
                        onCategoryClick = {})

                }

            }
        }
        composeTestRule.onNodeWithTag(TestTags.ProgressIndicator).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.HomeScreen).assertDoesNotExist()

    }

    @Test
    fun test_CategoryScreen_DataStateVisibilty_TrueExpected() {
        val categoryList = listOf("Electronic,clothing,shoes,books")
        composeTestRule.setContent {

            val categoryViewState = ViewState(
                isLoading = false, dataState = CategoryUiState(categorList = categoryList)
            )
            LoadingContentWrapper(isLoading = categoryViewState.isLoading, errorMessage = null) {
                CategoriesScreen(categoryUiState = categoryViewState.dataState!!,
                    productList = listOf(),
                    navToProductDetails = {},
                    onCategoryClick = {})

            }
        }
        composeTestRule.onNodeWithTag(TestTags.ProgressIndicator).assertDoesNotExist()
        composeTestRule.onNodeWithTag(TestTags.HomeScreen).assertIsDisplayed()

    }

    @Test
    fun test_CategoryScreen_ErrorStateVisibilty_TrueExpected() {
        val categoryViewState =
            ViewState(isLoading = false, dataState = null, errorMessage = "Something wrong!")
        composeTestRule.setContent {
            LoadingContentWrapper(
                isLoading = categoryViewState.isLoading,
                errorMessage = categoryViewState.errorMessage,
                showDialog = categoryViewState.errorMessage != null
            ) {
                categoryViewState.dataState?.let {
                    CategoriesScreen(categoryUiState =it,
                        productList = listOf(),
                        navToProductDetails = {},
                        onCategoryClick = {})

                }

            }
        }
        composeTestRule.onNodeWithTag(TestTags.ProgressIndicator).assertDoesNotExist()
        composeTestRule.onNodeWithTag(TestTags.AlertDialog).assertIsDisplayed()
    }

}