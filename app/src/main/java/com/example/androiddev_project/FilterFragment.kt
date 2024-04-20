package com.example.androiddev_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiddev_project.adapter.FilmAdapter
import com.example.androiddev_project.databinding.FragmentFilterBinding

class FilterFragment : Fragment(){
    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!
    private val adapter = FilmAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())


        binding.searchBtn.setOnClickListener {
            //performSearch(binding.searchEt.text.toString())
        }
    }



}