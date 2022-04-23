package com.christopherelias.mcdonalds.data.datasource

import com.christopherelias.mcdonalds.datasource.models.MenuApiModel

interface MenuRemoteDataSource {

    suspend fun getRemoteMenu(): List<MenuApiModel>
}