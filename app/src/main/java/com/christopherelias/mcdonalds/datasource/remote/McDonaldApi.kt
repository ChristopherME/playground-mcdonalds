package com.christopherelias.mcdonalds.datasource.remote

import com.christopherelias.mcdonalds.datasource.models.McDonaldApiModel
import com.christopherelias.mcdonalds.datasource.models.MenuApiModel
import retrofit2.http.GET

interface McDonaldApi {

    @GET("menu")
    suspend fun getMenu(): McDonaldApiModel
}