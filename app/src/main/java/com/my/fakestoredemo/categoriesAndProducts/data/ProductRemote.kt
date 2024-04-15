package com.my.fakestoredemo.categoriesAndProducts.data

import kotlinx.serialization.Serializable



@Serializable
data class ProductRemote(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)
@Serializable
data class Rating(
    val count: Int,
    val rate: Double
)