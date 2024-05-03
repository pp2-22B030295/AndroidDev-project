package com.example.movie_application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_application.MAIN
import com.example.movie_application.R
import com.example.movie_application.USER
import com.example.movie_application.USERS_LIB
import com.example.movie_application.User
import com.example.movie_application.adapter.FilmAdapter
import com.example.movie_application.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var adapter: FilmAdapter
    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userName: TextView = view.findViewById(R.id.user_name)
        val userLib: RecyclerView = view.findViewById(R.id.user_lib_list)
        val logoutButton: Button = view.findViewById(R.id.logout_button)

        userName.text = USER.name


        val currentUserPair = USERS_LIB.find { it.first == USER }
        val currentUserFilms = currentUserPair?.second ?: emptyList()

        adapter = FilmAdapter(currentUserFilms)
        userLib.adapter = adapter
        userLib.layoutManager = LinearLayoutManager(requireContext())


        binding.bNavMain.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.button_to_home -> {
                    MAIN.navController.navigate(R.id.action_profileFragment_to_mainFragment)
                }

                R.id.button_to_filter -> {
                    MAIN.navController.navigate(R.id.action_profileFragment_to_filterFragment)
                }
            }
            true
        }


        logoutButton.setOnClickListener{
            USER = User("***","***")
            MAIN.navController.navigate(R.id.action_profileFragment_to_mainFragment )
        }

    }


}
