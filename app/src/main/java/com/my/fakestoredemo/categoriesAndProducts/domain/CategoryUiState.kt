package com.my.fakestoredemo.categoriesAndProducts.domain


data class CategoryUiState(
    val categorList: List<String> = listOf(), val selectedCategoryIndex: Int = 0
)


