package com.example.movie_application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_application.R
import com.example.movie_application.USER
import com.example.movie_application.adapter.FilmAdapter
import com.example.movie_application.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var adapter: FilmAdapter
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userLib: RecyclerView = view.findViewById(R.id.user_lib_list)
        val logoutButton: Button = view.findViewById(R.id.logout_button)

        adapter = FilmAdapter(emptyList())
        userLib.adapter = adapter
        userLib.layoutManager = LinearLayoutManager(requireContext())

        viewModel.loadCurrentUserFilms(USER)
        viewModel.currentUserFilms.observe(viewLifecycleOwner) { films ->
            adapter.setData(films)
        }

        binding.userName.text = USER

        binding.bNavMain.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.button_to_home -> {
                    findNavController().navigate(R.id.action_profileFragment_to_mainFragment)
                }
                R.id.button_to_filter -> {
                    findNavController().navigate(R.id.action_profileFragment_to_filterFragment)
                }
            }
            true
        }

        logoutButton.setOnClickListener {
            USER = "***"
            findNavController().navigate(R.id.action_profileFragment_to_mainFragment)
        }
    }
}