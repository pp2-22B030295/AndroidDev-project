package com.example.movie_application.reqInfo


data class Human(
    val id: Int,
    val name: String,
    val birth: String, // Using String to simplify date handling, you can use a Date type with proper parsing if needed
    val bio: String
)