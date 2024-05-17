package com.example.movie_application

import com.example.movie_application.reqInfo.Concert
import com.example.movie_application.reqInfo.Human
import com.example.movie_application.reqInfo.TopFilm
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
