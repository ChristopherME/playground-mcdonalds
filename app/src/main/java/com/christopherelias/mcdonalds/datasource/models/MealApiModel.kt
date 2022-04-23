package com.christopherelias.mcdonalds.datasource.models

import com.squareup.moshi.Json

data class MealApiModel(
    val name: String,
    val description: String,
    @field:Json(name = "url") val imageUrl: String,
    val price: Double
)