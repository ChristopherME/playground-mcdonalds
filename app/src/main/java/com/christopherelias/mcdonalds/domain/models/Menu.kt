package com.christopherelias.mcdonalds.domain.models

data class Menu(
    val title: String,
    val meals: List<Meal>
)