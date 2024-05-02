package com.example.movie_application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.movie_application.MAIN
import com.example.movie_application.R
import com.example.movie_application.USER
import com.example.movie_application.User

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

        try {
            val user = USER
        } catch (e: UninitializedPropertyAccessException) {
            USER = User("***", "***")
        }






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