package com.example.movie_application

import com.example.movie_application.DataBase.DbHelper

lateinit var MAIN: MainActivity

lateinit var USER: User

val MOVIES: List<Film> = listOf(
    Film("Marvel","movie about heroes", 8.7, "action"),
    Film("Person","movie about person", 8.7, "action"),
    Film("Crab","movie about crab", 8.1, "fantasy"),
    Film("Palma","movie about palma", 4.7, "fantasy"),
    Film("Witch","movie about witch", 8.7, "horror"),
    Film("Wolf","movie about wolf", 8.7, "horror")
)