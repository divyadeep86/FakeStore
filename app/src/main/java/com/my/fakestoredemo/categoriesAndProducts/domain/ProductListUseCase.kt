package com.my.fakestoredemo.categoriesAndProducts.domain

import com.my.fakestoredemo.common.network.ResponseHandler
import com.my.fakestoredemo.common.state.DataState
import com.my.fakestoredemo.categoriesAndProducts.data.ProductRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductListUseCase(private val productRepo: ProductRepo) {

    fun getProductsByCategory(categoryName: String): Flow<DataState<List<Product>>> = flow {
        emit(DataState.loading(true))
        when (val result = productRepo.getProductByCategory(categoryName)) {

            is ResponseHandler.Success -> {
                emit(DataState.success(data = result.data.map { it.toProduct() }))
            }

            is ResponseHandler.Failure -> {
                emit(DataState.error(result.message))
            }
        }
    }
}