package com.example.recetaslist.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recetaslist.model.Recipe
@Composable
fun RecipeListScreen(
    recipes: List<Recipe>,
    onRecipeClick: (String) -> Unit
) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Platos encontrados", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        if (recipes.isEmpty()) {
            Text("No se encontraron recetas.")
        } else {
            LazyColumn {
                items(recipes) { recipe ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { onRecipeClick(recipe.name) }
                    ) {
                        Column(Modifier.padding(12.dp)) {
                            Text(recipe.name, style = MaterialTheme.typography.titleMedium)
                            Text("Ingredientes: ${recipe.ingredients.joinToString(", ")}")
                            Text("Preparación: ${recipe.preparation.take(50)}...")
                        }
                    }
                }
            }
        }
    }
}
