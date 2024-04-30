package com.example.movie_application.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_application.Film
import com.example.movie_application.MAIN
import com.example.movie_application.MOVIES
import com.example.movie_application.R
import com.example.movie_application.adapter.FilmAdapter

class FilterFragment : Fragment() {

    private lateinit var adapter: FilmAdapter
    private var films: List<Film> = MOVIES

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list : RecyclerView= view.findViewById(R.id.list)
        val actionButton : Button= view.findViewById(R.id.action_button)
        val showAllButton : Button= view.findViewById(R.id.show_all_button)
        val fantasyButton : Button= view.findViewById(R.id.fantasy_button)
        val horrorButton : Button= view.findViewById(R.id.horror_button)

        adapter = FilmAdapter(films)
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(requireContext())

        fantasyButton.setOnClickListener { filterFilmsByCategory("fantasy") }
        horrorButton.setOnClickListener { filterFilmsByCategory("horror") }
        actionButton.setOnClickListener { filterFilmsByCategory("action") }
        showAllButton.setOnClickListener { showAllFilms() }
    }

    private fun filterFilmsByCategory(category: String) {
        val filteredFilms = films.filter { it.category == category }
        updateRecyclerView(filteredFilms)
    }

    private fun showAllFilms() {
        updateRecyclerView(films)
    }

    private fun updateRecyclerView(films: List<Film>) {
        adapter.filmList = films
        adapter.notifyDataSetChanged()
    }

}