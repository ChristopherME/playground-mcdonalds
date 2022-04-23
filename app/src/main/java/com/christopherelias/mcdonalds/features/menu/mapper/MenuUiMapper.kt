package com.christopherelias.mcdonalds.features.menu.mapper

import com.christopherelias.mcdonalds.domain.models.Menu
import com.christopherelias.mcdonalds.features.menu.models.MenuUi

interface MenuUiMapper {

    suspend fun mapDomainMenuListToUi(
        domainMenuList: List<Menu>
    ): List<MenuUi>
}