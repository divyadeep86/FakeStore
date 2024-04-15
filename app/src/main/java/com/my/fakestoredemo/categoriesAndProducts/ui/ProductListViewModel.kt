package com.my.fakestoredemo.categoriesAndProducts.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.my.fakestoredemo.common.base.BaseViewModel
import com.my.fakestoredemo.categoriesAndProducts.domain.Product
import com.my.fakestoredemo.categoriesAndProducts.domain.ProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val productListUseCase: ProductListUseCase
) : BaseViewModel<List<Product>>(listOf()) {
    private val CATEGORY_NAME = "categoryName"

    init {
        savedStateHandle.getLiveData<String>(CATEGORY_NAME).asFlow().distinctUntilChanged().debounce(200).flatMapLatest {
            productListUseCase.getProductsByCategory(it)
        }.onEach {
            updateState(
                isLoading = it.isLoading,
                errorMessage = it.errorMessage,
                dataState = it.data?: listOf()
            )
        }.launchIn(viewModelScope)

    }
    fun getProductByCategory(categoryName: String) {
        savedStateHandle[CATEGORY_NAME] = categoryName
    }


}