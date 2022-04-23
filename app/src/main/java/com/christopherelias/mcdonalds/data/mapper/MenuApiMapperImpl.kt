package com.christopherelias.mcdonalds.data.mapper

import com.christopherelias.mcdonalds.datasource.models.MealApiModel
import com.christopherelias.mcdonalds.datasource.models.MenuApiModel
import com.christopherelias.mcdonalds.domain.models.Meal
import com.christopherelias.mcdonalds.domain.models.Menu
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MenuApiMapperImpl(
    private val computationDispatcher: CoroutineDispatcher
) : MenuApiMapper {

    override suspend fun mapMenuApiToDomain(
        menuApiList: List<MenuApiModel>
    ): List<Menu> = withContext(computationDispatcher) {
        menuApiList.map {
            Menu(
                title = it.name,
                meals = mapMealsApiToDomain(it.meals)
            )
        }
    }

    private fun mapMealsApiToDomain(
        mealsApi: List<MealApiModel>
    ): List<Meal> = mealsApi.map { apiModel ->
        Meal(
            name = apiModel.name,
            description = apiModel.description,
            imageUrl = apiModel.imageUrl,
            price = apiModel.price
        )
    }
}