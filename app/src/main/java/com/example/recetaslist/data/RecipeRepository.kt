package com.example.recetaslist.data

import com.example.recetaslist.model.Recipe

object RecipeRepository {
    // Lista de recetas iniciales
    private val recipes = mutableListOf(
        Recipe(
            "Majadito",
            listOf("Arroz", "Huevo", "Urucú", "Plátano", "Pollo"),
            "Cocer el arroz, mezclar con el pollo y el huevo, sale el majadito."
        ),
        Recipe(
            "Arroz a la valenciana",
            listOf("Arroz", "Tomate", "Pollo", "Ajo"),
            "Cocinar arroz con verduras, añadir pollo, servir caliente."
        ),
        Recipe(
            "Arrocito con huevo",
            listOf("Arroz", "Huevo"),
            "Cocer arroz, freír huevo y servir juntos."
        )
    )

    fun getAll(): List<Recipe> = recipes.toList()

    fun addRecipe(recipe: Recipe) {
        // Evitar duplicados por nombre (ignora mayúsculas/minúsculas)
        if (recipes.none { it.name.equals(recipe.name, ignoreCase = true) }) {
            recipes.add(recipe)
        }
    }
}

