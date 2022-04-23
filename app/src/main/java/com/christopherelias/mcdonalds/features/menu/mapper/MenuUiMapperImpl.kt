package com.christopherelias.mcdonalds.features.menu.mapper

import com.christopherelias.mcdonalds.domain.models.Meal
import com.christopherelias.mcdonalds.domain.models.Menu
import com.christopherelias.mcdonalds.features.menu.models.MealUi
import com.christopherelias.mcdonalds.features.menu.models.MenuUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.Locale

class MenuUiMapperImpl(
    private val computationDispatcher: CoroutineDispatcher
) : MenuUiMapper {

    override suspend fun mapDomainMenuListToUi(
        domainMenuList: List<Menu>
    ): List<MenuUi> = withContext(computationDispatcher) {
        domainMenuList.map { domainMenu ->
            MenuUi(
                title = domainMenu.title,
                meals = mapDomainMealsToUi(domainMeals = domainMenu.meals)
            )
        }
    }

    private fun mapDomainMealsToUi(
        domainMeals: List<Meal>
    ): List<MealUi> = domainMeals.map { domainMeal ->
        MealUi(
            name = domainMeal.name,
            description = domainMeal.description,
            imageUrl = domainMeal.imageUrl,
            price = String.format(Locale.getDefault(), "$%.2f", domainMeal.price)
        )
    }
}