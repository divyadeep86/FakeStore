package com.my.fakestoredemo

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule

import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sbtakehomeassignment.common.uiComponents.LoadingContentWrapper
import com.my.fakestoredemo.categoriesAndProducts.data.Rating
import com.my.fakestoredemo.categoriesAndProducts.domain.CategoryUiState
import com.my.fakestoredemo.categoriesAndProducts.domain.Product
import com.my.fakestoredemo.categoriesAndProducts.ui.CategoriesScreen
import com.my.fakestoredemo.categoriesAndProducts.ui.CategoriesViewModel
import com.my.fakestoredemo.categoriesAndProducts.ui.ProductListViewModel
import com.my.fakestoredemo.common.appNavigation.AppDestinations
import com.my.fakestoredemo.common.appNavigation.AppNavHost
import com.my.fakestoredemo.common.state.ViewState
import com.my.fakestoredemo.common.utils.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class NavigationTest {/*    @get:Rule(order = 0)
        val hiltRule = HiltAndroidRule(this)

        @get:Rule(order = 1)
        val androidComposeTestRule = createAndroidComposeRule<MainActivity>()

        private lateinit var navController: TestNavHostController


        @Before
        fun setupAppNavHost() {
                hiltRule.inject()
            androidComposeTestRule.setContent {
                *//* navController = TestNavHostController(LocalContext.current).apply {
                 navigatorProvider.addNavigator(ComposeNavigator())
             }
             //navController.*//*
             AppNavHost(navController = rememberNavController())
         *//*    categoriesViewModel = hiltViewModel()
             val categoryList = listOf("Electronic,clothing,shoes,books")
             val categoryViewState = ViewState(
                 isLoading = false, dataState = CategoryUiState(categorList = categoryList)
             )
             LoadingContentWrapper(isLoading = categoryViewState.isLoading, errorMessage = null) {
                 CategoriesScreen(categoryUiState = categoryViewState.dataState!!,
                     productList = listOf(),
                     navToProductDetails = {},
                     onCategoryClick = {})

             }*//*
         }
    }

    // Unit test
    @Test
    fun test_AppNavHostVisibility_TrueExpected() {
        androidComposeTestRule.onNodeWithTag(TestTags.HomeScreen, useUnmergedTree = true)
            .assertIsDisplayed()
    }


    @Test
    fun test_NavigationFrom_HomeScreen_To_ProductDetailsScreen_TrueExpected() {
        androidComposeTestRule.onNodeWithTag(TestTags.ProductItem).performClick()
        val route = navController.currentBackStackEntry?.destination?.route
        Assert.assertEquals(route, AppDestinations.PRODUCT_DETAILS_SCREEN)
    }*/

    /*    @get:Rule(order = 1)
        val composeTestRule = createAndroidComposeRule<MainActivity>()
        @get:Rule(order = 0)
        val hiltRule = HiltAndroidRule(this)
        @Before
        fun setupAppNavHost() {
            hiltRule.inject()
        }
        @Test
        fun appNavHost_displaysCategoriesScreen() {
           *//* composeTestRule.setContent {
            val navController = rememberNavController()
            AppNavHost(navController = navController)
        }*//*

        // Check if CategoriesScreen is displayed
        composeTestRule.onNodeWithTag(TestTags.HomeScreen).assertIsDisplayed()
    }*//*   @get:Rule(order = 0)
       val hiltRule = HiltAndroidRule(this)

       @get:Rule(order = 1)
       val composeTestRule = createAndroidComposeRule<MainActivity>()

       private lateinit var navController: TestNavHostController

       private lateinit var categoriesViewModel: CategoriesViewModel
       private lateinit var productViewModel: ProductListViewModel

       @Before
       fun setup() {
           hiltRule.inject()
           composeTestRule.activityRule.scenario.onActivity {
               navController = TestNavHostController(composeTestRule.activity).apply {
                   navigatorProvider.addNavigator(ComposeNavigator())
               }


               // Set up the mock CategoriesViewModel to return a custom categoryUiState
               val categoryList = listOf("Electronic", "Clothing", "Shoes", "Books")
               val categoryUiState = CategoryUiState(categorList = categoryList)
               val productList = listOf(
                   Product(
                       id = 5,
                       title = "Product 5",
                       price = 100.0,
                       category = "Electronic",
                       description = "Description 5",
                       image = "",
                       rating = Rating(rate = 4.5, count = 100)
                   )
               )
               it.setContent {



                   AppNavHost(navController = navController)

               }
           }


       }

       @Test
       fun navigationFromCategoriesScreenToProductDetailsScreen() {

           // Simulate a click on the product item
           categoriesViewModel.viewState
           composeTestRule.onNodeWithTag(TestTags.ProgressIndicator).assertIsDisplayed()
         *//*  val route = navController.currentBackStackEntry?.destination?.route
        // Check if the current destination is the ProductDetailsScreen
        Assert.assertEquals(route, AppDestinations.MAIN_SCREEN)*//*
    }*/
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    /*    @get:Rule(order = 1)
        val composeTestRule = createComposeRule()*/
    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        hiltRule.inject()
        /*    composeTestRule.setContent {
                hiltRule.inject()
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                AppNavHost(navController = navController)
            }*/
        composeTestRule.activityRule.scenario.onActivity {
            navController = TestNavHostController(composeTestRule.activity).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            it.setContent {
                val categoryList = listOf("Electronic,clothing,shoes,books")
                val categoryViewState = CategoryUiState(categorList = categoryList)
                val productList = listOf(
                    Product(
                        id = 5,
                        title = "Product 5",
                        price = 100.0,
                        category = "Electronic",
                        description = "Description 5",
                        image = "",
                        rating = Rating(rate = 4.5, count = 100)
                    )
                )

                AppNavHost(navController = navController)
                composeTestRule.activity.viewModels<CategoriesViewModel>().value.updateState(dataState = categoryViewState)
                composeTestRule.activity.viewModels<ProductListViewModel>().value.updateState(dataState = productList)
            }
        }
    }

    @Test
    fun test_NavigationOnClickProductItem_TrueExpected() {


     /*   composeTestRule.setContent {
            val categoryList = listOf("Electronic,clothing,shoes,books")
            val categoryViewState = ViewState(
                isLoading = false, dataState = CategoryUiState(categorList = categoryList)
            )
            val productList = listOf(
                Product(
                    id = 5,
                    title = "Product 5",
                    price = 100.0,
                    category = "Electronic",
                    description = "Description 5",
                    image = "",
                    rating = Rating(rate = 4.5, count = 100)
                )
            )
            LoadingContentWrapper(isLoading = categoryViewState.isLoading, errorMessage = null) {
                CategoriesScreen(categoryUiState = categoryViewState.dataState!!,
                    productList = productList,
                    navToProductDetails = {},
                    onCategoryClick = {})

            }

        }*/
        composeTestRule.onNodeWithTag(TestTags.HomeScreen).assertIsDisplayed()/* val route = navController.currentBackStackEntry?.destination?.route
         Assert.assertEquals(route, AppDestinations.PRODUCT_DETAILS_SCREEN)*/
    }

}