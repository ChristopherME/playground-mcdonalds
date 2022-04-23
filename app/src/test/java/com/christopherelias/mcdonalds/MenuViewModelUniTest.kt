package com.christopherelias.mcdonalds

import app.cash.turbine.test
import com.christopherelias.mcdonalds.domain.models.Meal
import com.christopherelias.mcdonalds.domain.models.Menu
import com.christopherelias.mcdonalds.domain.repository.MenuRepository
import com.christopherelias.mcdonalds.features.menu.mapper.MenuUiMapper
import com.christopherelias.mcdonalds.features.menu.mapper.MenuUiMapperImpl
import com.christopherelias.mcdonalds.features.menu.ui.MenuUiState
import com.christopherelias.mcdonalds.features.menu.ui.MenuViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@ExperimentalCoroutinesApi
class MenuViewModelUniTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val testCoroutineDispatcher = UnconfinedTestDispatcher()
    private val menuRepository = mockk<MenuRepository>()
    private val menuUiMapper: MenuUiMapper = MenuUiMapperImpl(testCoroutineDispatcher)

    private val viewModel = MenuViewModel(menuRepository, menuUiMapper)

    @Test
    fun `when fetchMenu gets triggered successfully then UI state must react accordingly`() =
        runTest {
            val domainMenu = provideDomainMenu()
            val expectedUiMenu = menuUiMapper.mapDomainMenuListToUi(domainMenu)

            coEvery { menuRepository.getMenu() } returns provideDomainMenu()

            viewModel.uiState.test {
                viewModel.fetchMenu()
                assertEquals(awaitItem(), MenuUiState(false))
                assertEquals(awaitItem(), MenuUiState(true))
                assertEquals(awaitItem(), MenuUiState(false, expectedUiMenu))
            }
        }

    @Test
    fun `when fetchMenu gets triggered and throws exception then UI state must react accordingly`() =
        runTest {

            val expectedException = Exception("Fail!")
            coEvery { menuRepository.getMenu() } throws expectedException

            viewModel.uiState.test {
                viewModel.fetchMenu()
                assertEquals(awaitItem(), MenuUiState(false))
                assertEquals(awaitItem(), MenuUiState(true))
                assertEquals(awaitItem(), MenuUiState(false, error = expectedException))
            }
        }

    private fun provideDomainMenu(): List<Menu> {
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
        return listOf(domainMenu)
    }

}