package com.example.recetaslist.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.recetaslist.data.RecipeRepository
import com.example.recetaslist.model.Recipe

class RecipeViewModel : ViewModel() {
    private val _recipes = mutableStateListOf<Recipe>().apply {
        addAll(RecipeRepository.getAll())
    }
    val recipes: List<Recipe> get() = _recipes

    fun addRecipe(recipe: Recipe) {
        // Evitar duplicados por nombre (ignora mayúsculas/minúsculas)
        if (_recipes.none { it.name.equals(recipe.name, ignoreCase = true) }) {
            _recipes.add(recipe)               // estado reactivo (Compose)
            RecipeRepository.addRecipe(recipe) // repositorio central en memoria
        }
    }

    fun getRecipesByIngredients(selected: List<String>): List<Recipe> {
        if (selected.isEmpty()) return emptyList()
        val wanted = selected.map { it.trim().lowercase() }.toSet()
        return _recipes.filter { recipe ->
            val ingr = recipe.ingredients.map { it.trim().lowercase() }.toSet()
            // ✅ basta con que la receta contenga al menos UN ingrediente seleccionado
            wanted.any { it in ingr }
        }
    }

    fun getRecipeById(name: String): Recipe? =
        _recipes.find { it.name == name }
}



