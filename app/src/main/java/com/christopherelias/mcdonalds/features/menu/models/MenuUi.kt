package com.christopherelias.mcdonalds.features.menu.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuUi(
    val title: String,
    val meals: List<MealUi>
) : Parcelable