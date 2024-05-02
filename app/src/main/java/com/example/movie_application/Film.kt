package com.example.movie_application

class Film(
    val title: String,
    val description: String,
    val rating: Double,
    val category: String,
    ){}

class User(
    val name: String,
    val password: String,
    ){}

class UserLibraryItem(
    val userId: Int,
    val filmId: Int
){}
