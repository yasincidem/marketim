package com.yasincidem.marketim.models

import com.squareup.moshi.Json

data class Order (
    @get:Json(name = "date") @Json(name = "date") val date: Int,
    @get:Json(name = "month") @Json(name = "month") val month: Int,
    @get:Json(name = "marketName") @Json(name = "marketName") val marketName: String,
    @get:Json(name = "orderName") @Json(name = "orderName") val orderName: String,
    @get:Json(name = "productPrice") @Json(name = "productPrice") val productPrice: Float,
    @get:Json(name = "productState") @Json(name = "productState") val productState: String,
    @get:Json(name = "productDetail") @Json(name = "productDetail") val productDetail: ProductDetail
)