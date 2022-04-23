package com.christopherelias.mcdonalds

import com.christopherelias.mcdonalds.data.mapper.MenuApiMapper
import com.christopherelias.mcdonalds.data.mapper.MenuApiMapperImpl
import com.christopherelias.mcdonalds.datasource.models.MealApiModel
import com.christopherelias.mcdonalds.datasource.models.MenuApiModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MenuApiMapperUnitTest {

    private val testCoroutineDispatcher = UnconfinedTestDispatcher()

    private val menuApiMapper: MenuApiMapper = MenuApiMapperImpl(testCoroutineDispatcher)

    @Test
    fun `when mapMenuApiToDomain gets invoked then it should return a menu domain instance`() =
        runTest {
            // Create fake objects
            val remoteMeal = MealApiModel(
                name = "Hot Chocolate",
                description = "bla bla bla",
                price = 1.89,
                imageUrl = "someImage.com"
            )
            val remoteMenu = MenuApiModel(
                name = "Bevareges",
                meals = listOf(remoteMeal)
            )
            // Test mapper implementation
            val domainMenu = menuApiMapper.mapMenuApiToDomain(listOf(remoteMenu)).first()
            val domainMeal = domainMenu.meals.first()

            // Assert Menu attrs
            assertEquals(remoteMenu.name, domainMenu.title)
            assertEquals(remoteMenu.meals.size, domainMenu.meals.size)
            // Assert Meal attrs
            assertEquals(remoteMeal.name, domainMeal.name)
            assertEquals(remoteMeal.description, domainMeal.description)
            assertEquals(remoteMeal.price, domainMeal.price, 0.0)
            assertEquals(remoteMeal.imageUrl, domainMeal.imageUrl)
        }
}