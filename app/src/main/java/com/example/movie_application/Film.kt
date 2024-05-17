package com.example.movie_application

class Film(
    val id: Int,
    val title: String,
    val description: String,
    val rating: Double,
    val category: String,
){}
class User(
    val name: String,
    val password: String,
){}
data class TopFilm(
    val id: Int,
    val name: String,
    val duration: Int,
    val score: Float,
    val desc: String
)
data class Human(
    val id: Int,
    val name: String,
    val birth: String,
    val bio: String
)
data class Concert(
    val id: Int,
    val name: String,
    val description: String,
    val award: String
)