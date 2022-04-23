package com.christopherelias.mcdonalds.di

import kotlinx.coroutines.Dispatchers

object CoroutinesDispatchersDiGraph {

    fun provideIoDispatcher() = Dispatchers.IO

    fun provideComputationDispatcher() = Dispatchers.Default
}