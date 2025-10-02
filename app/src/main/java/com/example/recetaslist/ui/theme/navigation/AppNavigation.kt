package com.example.recetasapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recetaslist.ui.screens.IngredientScreen
import com.example.recetaslist.ui.screens.RecipeListScreen
import com.example.recetaslist.ui.theme.screens.AddRecipePrompt
import com.example.recetaslist.ui.theme.screens.AddRecipeScreen
import com.example.recetaslist.ui.theme.screens.RecipeDetailScreen
import com.example.recetaslist.viewmodel.RecipeViewModel
@Composable
fun AppNavigation(viewModel: RecipeViewModel = viewModel()) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "ingredients") {

        // Pantalla 1 → Ingredientes
        composable("ingredients") {
            IngredientScreen(
                ingredients = listOf(
                    "Lechuga", "Arroz", "Huevo", "Tomate",
                    "Pollo", "Queso", "Fideo", "Ajo"
                ),
                onSearchClick = { selected ->
                    // 🔑 Forzamos siempre a consultar el estado más reciente
                    val recipes = viewModel.getRecipesByIngredients(selected)

                    if (recipes.isNotEmpty()) {
                        // ✅ Ahora sí: si hay recetas → vamos directo al listado
                        navController.navigate("list/${selected.joinToString(",")}")
                    } else {
                        // ✅ Solo si realmente no hay → mostramos prompt
                        navController.navigate("prompt")
                    }
                }
            )
        }


        // Pantalla 2 → Lista
        composable("list/{ingredients}") { backStackEntry ->
            val selectedIngredients =
                backStackEntry.arguments?.getString("ingredients")?.split(",") ?: emptyList()
            val recipes = viewModel.getRecipesByIngredients(selectedIngredients)
            RecipeListScreen(
                recipes = recipes,
                onRecipeClick = { recipeName ->
                    navController.navigate("detail/$recipeName")
                }
            )
        }

        // Pantalla 3 → Detalle
        composable("detail/{recipeName}") { backStackEntry ->
            val recipeName = backStackEntry.arguments?.getString("recipeName") ?: ""
            val recipe = viewModel.getRecipeById(recipeName)
            recipe?.let {
                RecipeDetailScreen(recipe = it)
            }
        }

        // Pantalla 4 → Prompt
        composable("prompt") {
            AddRecipePrompt(navController)
        }

        // Pantalla 5 → Formulario
        composable("addRecipe") {
            AddRecipeScreen(navController, viewModel)
        }
    }
}

