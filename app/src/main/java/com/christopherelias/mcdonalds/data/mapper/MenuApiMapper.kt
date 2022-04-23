package com.christopherelias.mcdonalds.data.mapper

import com.christopherelias.mcdonalds.datasource.models.MenuApiModel
import com.christopherelias.mcdonalds.domain.models.Menu

interface MenuApiMapper {
    suspend fun mapMenuApiToDomain(menuApiList: List<MenuApiModel>): List<Menu>
}