package com.example.movie_application


lateinit var USER: String

var USERS_LIB: List<Pair<String, List<Film>>> = emptyList()

val MOVIES: List<Film> = listOf(
    Film(1,"Marvel","movie about heroes", 8.7, "action"),
    Film(2,"Person","movie about person", 8.7, "action"),
    Film(3,"Crab","movie about crab", 8.1, "fantasy"),
    Film(4,"Palma","movie about palma", 4.7, "fantasy"),
    Film(5,"Witch","movie about witch", 8.7, "horror"),
    Film(6,"Wolf","movie about wolf", 8.7, "horror")
)