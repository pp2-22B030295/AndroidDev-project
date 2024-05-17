package com.example.movie_application.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.movie_application.R

import com.bumptech.glide.Glide
import com.example.movie_application.ApiService
import com.example.movie_application.RetrofitClient
import com.example.movie_application.databinding.FragmentConcertBinding
import com.example.movie_application.databinding.FragmentHumanBinding
import com.example.movie_application.reqInfo.Human

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class human : Fragment() {
    lateinit var binding: FragmentHumanBinding
    private val BASE_URL = "http://10.0.2.2:8000/api/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHumanBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView1 = view.findViewById<ImageView>(R.id.firstHuman)
        val imageView2 = view.findViewById<ImageView>(R.id.secondHuman)
        val imageView3 = view.findViewById<ImageView>(R.id.thirdHuman)

        Glide.with(this)
            .load("https://www.instantaiprompt.com/wp-content/uploads/2023/11/emma-watson-ai-portrait.jpg")
            .into(imageView1)
        Glide.with(this)
            .load("https://upload.wikimedia.org/wikipedia/commons/2/21/Johnny_Depp_2020.jpg")
            .into(imageView2)
        Glide.with(this)
            .load("https://s6.stc.all.kpcdn.net/afisha/msk/wp-content/uploads/sites/5/2023/12/bred-pitt.jpg")
            .into(imageView3)

        val apiService = RetrofitClient.getClient(BASE_URL).create(ApiService::class.java)

        fetchHumans(apiService)
    }

    private fun fetchHumans(apiService: ApiService) {
        val call = apiService.getHumans()
        call.enqueue(object : Callback<List<Human>> {
            override fun onResponse(call: Call<List<Human>>, response: Response<List<Human>>) {
                if (response.isSuccessful) {
                    val humans = response.body()
                    binding.apply {
                        textView2.setText(humans!![0].name)
                        textView3.setText(humans!![0].birth)
                        textView4.setText(humans!![0].bio)

                        textView5.setText(humans!![1].name)
                        textView6.setText(humans!![1].birth)
                        textView7.setText(humans!![1].bio)

                        textView8.setText(humans!![2].name)
                        textView9.setText(humans!![2].birth)
                        textView10.setText(humans!![2].bio)
                    }
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
}