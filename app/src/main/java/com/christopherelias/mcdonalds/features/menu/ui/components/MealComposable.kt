package com.christopherelias.mcdonalds.features.menu.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.christopherelias.mcdonalds.R
import com.christopherelias.mcdonalds.features.menu.models.MealUi

@Composable
fun MealsItems(
    meals: List<MealUi>,
    onMealClicked: (meal: MealUi) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier) {
        items(meals) { meal ->
            MealItem(
                meal = meal,
                onMealClicked = onMealClicked,
                modifier = modifier
            )
        }
    }
}

@Composable
fun MealItem(
    meal: MealUi,
    onMealClicked: (meal: MealUi) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(dimensionResource(id = R.dimen.meal_width))
            .padding(8.dp)
            .clickable {
                onMealClicked(meal)
            }
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(16.dp)

    ) {
        Image(
            contentDescription = null,
            painter = rememberAsyncImagePainter(meal.imageUrl),
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.meal_height_l))
                .align(Alignment.CenterHorizontally)
        )
        Text(
            color = Color.Black,
            modifier = modifier
                .padding(top = 24.dp, bottom = 8.dp)
                .align(Alignment.CenterHorizontally),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.body2,
            text = meal.name,
            textAlign = TextAlign.Center
        )
    }
}