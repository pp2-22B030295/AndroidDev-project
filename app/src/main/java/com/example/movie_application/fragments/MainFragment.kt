package com.example.movie_application.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.movie_application.MAIN
import com.example.movie_application.R
import com.example.movie_application.USER
import com.example.movie_application.User
import com.example.movie_application.databinding.ActivityMainBinding
import com.example.movie_application.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val buttonLogin: Button = view.findViewById(R.id.button_to_login)
//        val buttonFilter: Button = view.findViewById(R.id.button_to_filter)
//        val buttonProfile: Button = view.findViewById(R.id.button_to_profile)

        try {
            val user = USER
        } catch (e: UninitializedPropertyAccessException) {
            USER = User("***", "***")
        }
//        buttonLogin.setOnClickListener{
//            MAIN.navController.navigate(R.id.action_mainFragment_to_authFragment)
//        }
//        buttonProfile.setOnClickListener{
//            MAIN.navController.navigate(R.id.action_mainFragment_to_profileFragment)
//        }
//        buttonFilter.setOnClickListener{
//            MAIN.navController.navigate(R.id.action_mainFragment_to_filterFragment)
//        }
        binding.bNavMain.setOnItemSelectedListener {
            when(it.itemId){
                R.id.button_to_filter->{
                    MAIN.navController.navigate(R.id.action_mainFragment_to_filterFragment)
                }
                R.id.button_to_login->{
                    MAIN.navController.navigate(R.id.action_mainFragment_to_authFragment)
                }
                R.id.button_to_profile->{
                    MAIN.navController.navigate(R.id.action_mainFragment_to_profileFragment)
                }
            }

            true
        }
    }

    override fun onResume() {
        super.onResume()
        binding.bNavMain.selectedItemId=R.id.button_to_home
    }



}