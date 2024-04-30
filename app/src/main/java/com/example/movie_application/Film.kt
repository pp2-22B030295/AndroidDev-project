package com.example.movie_application

data class Film(
    val title: String,
    val description: String,
    val rating: Double,
    val category: String,
)

data class User(
    val name: String,
    val password: String,
)
