package com.example.movie_application.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movie_application.ApiService
import com.example.movie_application.RetrofitClient
import com.example.movie_application.TopFilm
import com.example.movie_application.databinding.FragmentFilmBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class filmFragment : Fragment() {
    lateinit var binding: FragmentFilmBinding
    private val BASE_URL = "http://10.0.2.2:8000/api/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiService = RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)

        fetchFilms(apiService)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmBinding.inflate(inflater)
        return binding.root
    }

    private fun fetchFilms(apiService: ApiService) {
        val call = apiService.getFilms()
        call.enqueue(object : Callback<List<TopFilm>> {
            override fun onResponse(call: Call<List<TopFilm>>, response: Response<List<TopFilm>>) {
                if (response.isSuccessful) {
                    val films = response.body()
                    binding.apply {
                        textView2.setText(films!![0].name)
                        textView3.setText(films!![0].desc)
                        textView4.setText(films!![0].score.toString())

                        textView5.setText(films!![1].name)
                        textView6.setText(films!![1].desc)
                        textView7.setText(films!![1].score.toString())

                        textView8.setText(films!![2].name)
                        textView9.setText(films!![2].desc)
                        textView10.setText(films!![2].score.toString())
                    }
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