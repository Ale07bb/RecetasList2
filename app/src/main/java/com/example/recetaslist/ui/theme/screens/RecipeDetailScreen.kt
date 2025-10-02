package com.example.recetaslist.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recetaslist.model.Recipe

@Composable
fun RecipeDetailScreen(recipe: Recipe) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = recipe.name,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text("Ingredientes:", style = MaterialTheme.typography.titleMedium)
        recipe.ingredients.forEachIndexed { index, ingredient ->
            Text("${index + 1}.- $ingredient")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Preparación:", style = MaterialTheme.typography.titleMedium)
        Text(recipe.preparation, style = MaterialTheme.typography.bodyMedium)
    }
}