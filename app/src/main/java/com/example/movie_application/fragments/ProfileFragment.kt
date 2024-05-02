package com.example.movie_application.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_application.MAIN
import com.example.movie_application.R
import com.example.movie_application.USER
import com.example.movie_application.User

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userName: TextView = view.findViewById(R.id.user_name)
        val userLib: RecyclerView = view.findViewById(R.id.user_lib_list)
        val logoutButton: Button = view.findViewById(R.id.logout_button)


        userName.text = USER.name



        logoutButton.setOnClickListener{
            USER = User("***","***")
            MAIN.navController.navigate(R.id.action_profileFragment_to_mainFragment )
        }

    }

}