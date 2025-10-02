package com.example.recetaslist.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun AddRecipePrompt(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("No hay esta receta", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))
        Text("¿Quieres agregarla?", style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(24.dp))

        Button(onClick = { navController.navigate("addRecipe") }) {
            Text("Agregar receta")
        }
    }
}