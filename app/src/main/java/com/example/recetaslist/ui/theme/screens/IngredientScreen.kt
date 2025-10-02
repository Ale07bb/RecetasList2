package com.example.recetaslist.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun IngredientScreen(
    ingredients: List<String>,
    onSearchClick: (List<String>) -> Unit
) {
    // Lista de seleccionados
    val selectedIngredients = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Selecciona ingredientes",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Mostramos botones con estilo chip
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            maxItemsInEachRow = 3
        ) {
            ingredients.forEach { ingredient ->
                val isSelected = selectedIngredients.contains(ingredient)
                Surface(
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable {
                            if (isSelected) {
                                selectedIngredients.remove(ingredient)
                            } else {
                                selectedIngredients.add(ingredient)
                            }
                        },
                    color = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray,
                    shape = RoundedCornerShape(16.dp),
                    tonalElevation = 4.dp
                ) {
                    Text(
                        text = ingredient,
                        color = if (isSelected) Color.White else Color.Black,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { onSearchClick(selectedIngredients.toList()) }, // 👈 antes pasabas la lista mutable
            enabled = selectedIngredients.isNotEmpty(),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text("Buscar receta")
        }
    }
}