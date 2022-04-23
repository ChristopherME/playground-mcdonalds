package com.christopherelias.mcdonalds.features.menu.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.christopherelias.mcdonalds.features.menu.models.MealUi
import com.christopherelias.mcdonalds.features.menu.models.MenuUi

@Composable
fun MenuItems(
    menuItems: List<MenuUi>,
    onMealClicked: (meal: MealUi) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(menuItems) { menu ->
            MenuItem(menu = menu, onMealClicked = onMealClicked)
        }
    }
}

@Composable
fun MenuItem(
    menu: MenuUi,
    onMealClicked: (meal: MealUi) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = menu.title,
            style = MaterialTheme.typography.h5,
            color = Color.Black,
            modifier = Modifier.padding(16.dp)
        )
        MealsItems(meals = menu.meals, onMealClicked = onMealClicked)
    }
}