package com.christopherelias.mcdonalds.datasource.models

import com.squareup.moshi.Json

data class MenuApiModel(
    val name: String,
    @field:Json(name = "items") val meals: List<MealApiModel>
)