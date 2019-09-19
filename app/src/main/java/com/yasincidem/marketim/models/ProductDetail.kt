package com.yasincidem.marketim.models

import com.squareup.moshi.Json

data class ProductDetail (
    @get:Json(name = "orderDetail") @Json(name = "orderDetail") val orderDetail: String,
    @get:Json(name = "summaryPrice") @Json(name = "summaryPrice") val summaryPrice: Float
)