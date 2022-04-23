package com.christopherelias.mcdonalds.domain.repository

import com.christopherelias.mcdonalds.domain.models.Menu

interface MenuRepository {
    suspend fun getMenu(): List<Menu>
}