package com.my.fakestoredemo.categoriesAndProducts.domain

import com.my.fakestoredemo.categoriesAndProducts.data.ProductRemote
import com.my.fakestoredemo.categoriesAndProducts.data.Rating
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)

fun ProductRemote.toProduct(): Product {
    return Product(
        category = category,
        description = description,
        id = id,
        image = image,
        price = price,
        rating = rating,
        title = title
    )
}
