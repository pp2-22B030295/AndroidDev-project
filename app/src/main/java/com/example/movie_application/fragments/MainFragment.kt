package com.example.movie_application.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.movie_application.Film
import com.example.movie_application.MAIN
import com.example.movie_application.MOVIES
import com.example.movie_application.R

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonLogin: Button = view.findViewById(R.id.button_to_login)
        val buttonFilter: Button = view.findViewById(R.id.button_to_filter)
        val buttonProfile: Button = view.findViewById(R.id.button_to_profile)

        MOVIES = listOf(
            Film("Marvel","movie about heroes", 8.7, "action"),
            Film("Person","movie about person", 8.7, "action"),
            Film("Crab","movie about crab", 8.1, "fantasy"),
            Film("Palma","movie about palma", 4.7, "fantasy"),
            Film("Witch","movie about witch", 8.7, "horror"),
            Film("Wolf","movie about wolf", 8.7, "horror")
        )

        buttonLogin.setOnClickListener{
            MAIN.navController.navigate(R.id.action_mainFragment_to_authFragment)
        }
        buttonProfile.setOnClickListener{
            MAIN.navController.navigate(R.id.action_mainFragment_to_profileFragment)
        }
        buttonFilter.setOnClickListener{
            MAIN.navController.navigate(R.id.action_mainFragment_to_filterFragment)
        }
    }

}