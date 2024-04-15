package com.my.fakestoredemo.categoriesAndProducts.domain

import com.my.fakestoredemo.categoriesAndProducts.data.CategoriesRepo
import com.my.fakestoredemo.common.network.ResponseHandler
import com.my.fakestoredemo.common.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoriesUseCase(private val categoriesRepo: CategoriesRepo) {

    fun getCategories(): Flow<DataState<CategoryUiState>> = flow {
        emit(DataState.loading(true))
        when (val categories=categoriesRepo.getCategories()) {
            is ResponseHandler.Success -> {
                emit(DataState.success(data = CategoryUiState(categorList = categories.data)))
            }
            is ResponseHandler.Failure -> {
                emit(DataState.error(categories.message))
            }
        }

    }

}