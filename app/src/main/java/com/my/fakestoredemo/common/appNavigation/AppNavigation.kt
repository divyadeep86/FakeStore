package com.my.fakestoredemo.common.appNavigation

import androidx.navigation.NavHostController

object AppDestinations{
    const val MAIN_SCREEN = "mainScreen"
    const val LAUNCH_SCREEN ="launchScreen"
    const val PRODUCT_DETAILS_SCREEN ="productDetailsScreen"
}

class AppNavigationActions(navHostController: NavHostController){
    val back:() -> Unit = {
        navHostController.navigateUp()
    }
    val navToMainScreen:()->Unit = {
        navHostController.navigate(AppDestinations.MAIN_SCREEN)
    }
    val navToLaunchScreen: () -> Unit = {
        navHostController.navigate(AppDestinations.LAUNCH_SCREEN)
    }
    val navToProductDetailsScreen: () -> Unit = {
        navHostController.navigate(AppDestinations.PRODUCT_DETAILS_SCREEN)
    }
}