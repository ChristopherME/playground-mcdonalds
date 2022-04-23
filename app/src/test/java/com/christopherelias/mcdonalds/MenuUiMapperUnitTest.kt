package com.christopherelias.mcdonalds

import com.christopherelias.mcdonalds.domain.models.Meal
import com.christopherelias.mcdonalds.domain.models.Menu
import com.christopherelias.mcdonalds.features.menu.mapper.MenuUiMapper
import com.christopherelias.mcdonalds.features.menu.mapper.MenuUiMapperImpl
import com.christopherelias.mcdonalds.features.menu.models.MealUi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class MenuUiMapperUnitTest {

    private val testCoroutineDispatcher = UnconfinedTestDispatcher()

    private val menuUiMapper: MenuUiMapper = MenuUiMapperImpl(testCoroutineDispatcher)

    @Test
    fun `when mapDomainMenuListToUi gets invoked then it should return a menu UI instance`() =
        runTest {
            val domainMeal = Meal(
                name = "Hot Chocolate",
                description = "bla bla bla",
                imageUrl = "image.com",
                price = 1.1
            )
            val domainMenu = Menu(
                title = "Bevareges",
                meals = listOf(domainMeal)
            )

            val uiMenu = menuUiMapper.mapDomainMenuListToUi(listOf(domainMenu)).first()
            val uiMeal = uiMenu.meals.first()

            Assert.assertEquals(domainMenu.title, uiMenu.title)
            Assert.assertEquals(domainMenu.meals.size, uiMenu.meals.size)

            Assert.assertEquals(domainMeal.name, uiMeal.name)
            Assert.assertEquals(domainMeal.description, uiMeal.description)
            Assert.assertEquals(
                String.format(Locale.getDefault(), "$%.2f", domainMeal.price),
                uiMeal.price
            )
            Assert.assertEquals(domainMeal.imageUrl, uiMeal.imageUrl)
        }

}