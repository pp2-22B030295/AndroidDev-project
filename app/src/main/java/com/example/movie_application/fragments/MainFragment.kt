package com.example.movie_application.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movie_application.R
import com.example.movie_application.USER
import com.example.movie_application.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            val user = USER
        } catch (e: UninitializedPropertyAccessException) {
            USER = "***"
        }
        if(USER != "***"){
            binding.bNavMain.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.button_to_filter -> {
                        findNavController().navigate(R.id.action_mainFragment_to_filterFragment)
                    }
                    R.id.button_to_profile -> {
                        findNavController().navigate(R.id.action_mainFragment_to_profileFragment)
                    }
                }
                true
            }
        }
        else{
            binding.bNavMain.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.button_to_filter -> {
                        findNavController().navigate(R.id.action_mainFragment_to_filterFragment)
                    }
                    R.id.button_to_profile -> {
                        findNavController().navigate(R.id.action_mainFragment_to_authFragment)
                    }
                }
                true
            }
        }


    }

    override fun onResume() {
        super.onResume()
        binding.bNavMain.selectedItemId=R.id.button_to_home
    }



}