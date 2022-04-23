package com.christopherelias.mcdonalds.data.repository

import com.christopherelias.mcdonalds.data.mapper.MenuApiMapper
import com.christopherelias.mcdonalds.data.datasource.MenuRemoteDataSource
import com.christopherelias.mcdonalds.domain.models.Menu
import com.christopherelias.mcdonalds.domain.repository.MenuRepository

class MenuRepositoryImpl(
    private val remoteDataSource: MenuRemoteDataSource,
    private val mapper: MenuApiMapper,
) : MenuRepository {

    override suspend fun getMenu(): List<Menu> {
        val response = remoteDataSource.getRemoteMenu()
        return mapper.mapMenuApiToDomain(menuApiList = response)
    }
}