package com.my.fakestoredemo.categoriesAndProducts.di

import com.my.fakestoredemo.categoriesAndProducts.data.CategoriesApi
import com.my.fakestoredemo.categoriesAndProducts.data.CategoriesRepo
import com.my.fakestoredemo.categoriesAndProducts.data.ProductApi
import com.my.fakestoredemo.categoriesAndProducts.data.ProductRepo
import com.my.fakestoredemo.categoriesAndProducts.domain.CategoriesUseCase
import com.my.fakestoredemo.categoriesAndProducts.domain.ProductListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoriesAndProductsModule {

    @Provides
    @Singleton
    fun provideCategoriesApi(retrofit: Retrofit): CategoriesApi {
        return retrofit.create(CategoriesApi::class.java)
    }


    @Provides
    @Singleton
    fun provideCategoriesRepository(categoriesApi: CategoriesApi): CategoriesRepo {
        return CategoriesRepo(categoriesApi = categoriesApi)
    }

    @Provides
    @Singleton
    fun provideCategoryListUseCase(categoriesRepo: CategoriesRepo): CategoriesUseCase {
        return CategoriesUseCase(categoriesRepo = categoriesRepo)
    }

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(productApi: ProductApi): ProductRepo {
        return ProductRepo(productApi = productApi)
    }

    @Provides
    @Singleton
    fun provideProductListUseCase(productRepo: ProductRepo): ProductListUseCase {
        return ProductListUseCase(productRepo = productRepo)
    }


}