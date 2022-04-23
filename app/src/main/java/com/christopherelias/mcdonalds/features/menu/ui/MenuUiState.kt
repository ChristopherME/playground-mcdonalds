package com.christopherelias.mcdonalds.features.menu.ui

import com.christopherelias.mcdonalds.features.menu.models.MenuUi

data class MenuUiState(
    val loading: Boolean = false,
    val menuItems: List<MenuUi> = emptyList(),
    val error: Throwable? = null
)