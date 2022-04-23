package com.christopherelias.mcdonalds.domain.models

data class Meal(
    val name: String,
    val description: String,
    val imageUrl: String,
    val price: Double
)