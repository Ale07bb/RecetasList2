package com.example.recetaslist.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.recetaslist.model.Recipe
import com.example.recetaslist.viewmodel.RecipeViewModel
@Composable
fun AddRecipeScreen(
    navController: NavHostController,
    viewModel: RecipeViewModel
) {
    var name by rememberSaveable { mutableStateOf("") }
    var preparation by rememberSaveable { mutableStateOf("") }
    val ingredients = rememberSaveable { mutableStateListOf<String>() }
    var ingInput by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text("Agregar nueva receta", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre del plato") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = ingInput,
                onValueChange = { ingInput = it },
                label = { Text("Ingrediente") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                if (ingInput.isNotBlank() && !ingredients.contains(ingInput.trim())) {
                    ingredients.add(ingInput.trim())
                    ingInput = ""
                }
            }) {
                Text("+")
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 150.dp)
                .padding(top = 8.dp)
        ) {
            items(ingredients) { ing ->
                Text("• $ing")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = preparation,
            onValueChange = { preparation = it },
            label = { Text("Preparación") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            maxLines = 5
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (name.isNotBlank() && ingredients.isNotEmpty() && preparation.isNotBlank()) {
                    val recipe = Recipe(
                        name = name,
                        ingredients = ingredients.toList(),
                        preparation = preparation
                    )
                    viewModel.addRecipe(recipe)

                    navController.navigate("ingredients") {
                        popUpTo("ingredients") { inclusive = true }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Guardar receta")
        }
    }
}



