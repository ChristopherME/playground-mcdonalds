package com.christopherelias.mcdonalds.datasource.remote

import com.christopherelias.mcdonalds.data.datasource.MenuRemoteDataSource
import com.christopherelias.mcdonalds.datasource.models.MenuApiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MenuRemoteDataSourceImpl(
    private val apiService: McDonaldApi,
    private val ioDispatcher: CoroutineDispatcher
) : MenuRemoteDataSource {
    override suspend fun getRemoteMenu(): List<MenuApiModel> = withContext(ioDispatcher) {
        apiService.getMenu().menus
    }
}