package com.christopherelias.mcdonalds.di

import com.christopherelias.mcdonalds.data.mapper.MenuApiMapper
import com.christopherelias.mcdonalds.data.mapper.MenuApiMapperImpl
import com.christopherelias.mcdonalds.data.datasource.MenuRemoteDataSource
import com.christopherelias.mcdonalds.data.repository.MenuRepositoryImpl
import com.christopherelias.mcdonalds.datasource.remote.McDonaldApi
import com.christopherelias.mcdonalds.datasource.remote.MenuRemoteDataSourceImpl
import com.christopherelias.mcdonalds.domain.repository.MenuRepository
import com.christopherelias.mcdonalds.features.menu.mapper.MenuUiMapper
import com.christopherelias.mcdonalds.features.menu.mapper.MenuUiMapperImpl

object MenuFeatureDiGraph {

    private fun provideMenuApiMapper(): MenuApiMapper =
        MenuApiMapperImpl(
            computationDispatcher = CoroutinesDispatchersDiGraph
                .provideComputationDispatcher()
        )

    private fun provideMenuRemoteDataSource(): MenuRemoteDataSource =
        MenuRemoteDataSourceImpl(
            apiService = RetrofitDiGraph.provideRetrofitBuilder()
                .create(McDonaldApi::class.java),
            ioDispatcher = CoroutinesDispatchersDiGraph
                .provideIoDispatcher()
        )

    fun provideMenuUiMapper(): MenuUiMapper =
        MenuUiMapperImpl(
            computationDispatcher = CoroutinesDispatchersDiGraph
                .provideComputationDispatcher()
        )

    fun provideRepository(): MenuRepository =
        MenuRepositoryImpl(
            remoteDataSource = provideMenuRemoteDataSource(),
            mapper = provideMenuApiMapper()
        )
}