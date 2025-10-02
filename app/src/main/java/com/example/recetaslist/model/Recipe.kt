package com.example.recetaslist.model

data class Recipe(
    val name: String,
    val ingredients: List<String>,
    val preparation: String
)