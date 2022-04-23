package com.christopherelias.mcdonalds.features.menu.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealUi(
    val name: String,
    val description: String,
    val imageUrl: String,
    val price: String
) : Parcelable