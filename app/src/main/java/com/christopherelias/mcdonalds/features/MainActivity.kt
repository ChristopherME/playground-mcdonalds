package com.christopherelias.mcdonalds.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.christopherelias.mcdonalds.di.MenuFeatureDiGraph
import com.christopherelias.mcdonalds.features.menu.ui.MenuUiState
import com.christopherelias.mcdonalds.features.menu.ui.MenuViewModel
import com.christopherelias.mcdonalds.features.menu.ui.MenuViewModelFactory
import com.christopherelias.mcdonalds.features.menu.ui.components.MenuItems
import com.christopherelias.mcdonalds.features.theme.McDonaldsTheme

class MainActivity : AppCompatActivity() {

    private val viewModel: MenuViewModel by viewModels {
        MenuViewModelFactory(
            repository = MenuFeatureDiGraph.provideRepository(),
            uiMapper = MenuFeatureDiGraph.provideMenuUiMapper()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            McDonaldsTheme {
                // A surface container using the 'background' color from the theme
                Content(viewModel = viewModel)
                viewModel.fetchMenu()
            }
        }
    }
}

@Composable
fun Content(viewModel: MenuViewModel) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val uiState: MenuUiState by viewModel.uiState.collectAsState()
        with(uiState) {
            if (!loading && menuItems.isNotEmpty()) {
                MenuItems(menuItems = menuItems, onMealClicked = {})
            }
        }
    }
}