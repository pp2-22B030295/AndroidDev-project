package com.example.androiddev_project

data class Film(
    val id: Int,
    val title: String,
    val description: String,
    val poster: Int,//String
    val duration: Int,
    val director: String,
    val language: Language,
    val rating: Double,
    val category: List<Category>,
)

data class Review(
    val id: Int,
    val author: User,
    val title: String,
    val text: String,
)

data class User(
    val id: Int,
    val name: String,
    val password: String,
    val library: List<Film>,
    val reviews: List<Review>,
)

enum class Category {
    ROMANCE, ADVENTURE, HORROR, COMEDY, SCI_FI, FANTASY
}
enum class Language{
    ENG, RUS, KAZ
}
