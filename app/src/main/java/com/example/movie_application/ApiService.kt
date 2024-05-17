package com.example.movie_application

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("concerts/")
    fun getConcerts(): Call<List<Concert>>

    @GET("humans/")
    fun getHumans(): Call<List<Human>>

    @GET("films/")
    fun getFilms(): Call<List<TopFilm>>
}

