package com.my.fakestoredemo.categoriesAndProducts.ui

import androidx.lifecycle.viewModelScope
import com.my.fakestoredemo.categoriesAndProducts.domain.CategoriesUseCase
import com.my.fakestoredemo.categoriesAndProducts.domain.CategoryUiState
import com.my.fakestoredemo.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val categoriesUseCase: CategoriesUseCase) :
    BaseViewModel<CategoryUiState>(
        CategoryUiState()
    ) {

    init {
        categoriesUseCase.getCategories().onEach {
            updateState(
                isLoading = it.isLoading, dataState = it.data, errorMessage = it.errorMessage
            )
        }.launchIn(viewModelScope)

    }

    fun onClickCategory(index: Int) {
        updateState(
            dataState = viewState.value.dataState?.copy(selectedCategoryIndex = index)
        )
    }
}