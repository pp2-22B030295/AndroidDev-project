package com.example.movie_application.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movie_application.ApiService
import com.example.movie_application.R
import com.example.movie_application.RetrofitClient
import com.example.movie_application.databinding.FragmentConcertBinding
import com.example.movie_application.databinding.FragmentMainBinding
import com.example.movie_application.reqInfo.Concert

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Concert : Fragment() {
    lateinit var binding: FragmentConcertBinding
    private val BASE_URL = "http://10.0.2.2:8000/api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiService = RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)

        fetchConcerts(apiService)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConcertBinding.inflate(inflater)
        return binding.root
    }

    private fun fetchConcerts(apiService: ApiService) {
        val call = apiService.getConcerts()
        call.enqueue(object : Callback<List<Concert>> {
            override fun onResponse(call: Call<List<Concert>>, response: Response<List<Concert>>) {
                if (response.isSuccessful) {
                    val concerts = response.body()
                    binding.apply {
                        textView2.setText(concerts!![0].name)
                        textView3.setText(concerts!![0].description)
                        textView4.setText(concerts!![0].award)

                        textView5.setText(concerts!![1].name)
                        textView6.setText(concerts!![1].description)
                        textView7.setText(concerts!![1].award)

                        textView8.setText(concerts!![2].name)
                        textView9.setText(concerts!![2].description)
                        textView10.setText(concerts!![2].award)
                    }
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

}


