package com.example.movie_application

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.movie_application.databinding.ActivityMainBinding
import com.example.movie_application.Concert
import com.example.movie_application.Human
import com.example.movie_application.TopFilm

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val BASE_URL = "http://10.0.2.2:8000/api/"


    lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        val apiService = RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)

        fetchConcerts(apiService)
        fetchHumans(apiService)
        fetchFilms(apiService)
    }
    private fun fetchConcerts(apiService: ApiService) {
        val call = apiService.getConcerts()
        call.enqueue(object : Callback<List<Concert>> {
            override fun onResponse(call: Call<List<Concert>>, response: Response<List<Concert>>) {
                if (response.isSuccessful) {
                    val concerts = response.body()

                    Log.d("API", "Concerts: $concerts")
                } else {
                    Log.e("API_ERROR", "Response error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Concert>>, t: Throwable) {
                Log.e("API_ERROR", "Network error: ${t.message}")
            }
        })
    }

    private fun fetchHumans(apiService: ApiService) {
        val call = apiService.getHumans()
        call.enqueue(object : Callback<List<Human>> {
            override fun onResponse(call: Call<List<Human>>, response: Response<List<Human>>) {
                if (response.isSuccessful) {
                    val humans = response.body()

                    Log.d("API", "Humans: $humans")
                } else {
                    Log.e("API_ERROR", "Response error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Human>>, t: Throwable) {
                Log.e("API_ERROR", "Network error: ${t.message}")
            }
        })
    }

    private fun fetchFilms(apiService: ApiService) {
        val call = apiService.getFilms()
        call.enqueue(object : Callback<List<TopFilm>> {
            override fun onResponse(call: Call<List<TopFilm>>, response: Response<List<TopFilm>>) {
                if (response.isSuccessful) {
                    val films = response.body()

                    Log.d("API", "Films: $films")
                } else {
                    Log.e("API_ERROR", "Response error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<TopFilm>>, t: Throwable) {
                Log.e("API_ERROR", "Network error: ${t.message}")
            }
        })
    }
}