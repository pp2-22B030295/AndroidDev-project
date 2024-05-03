package com.example.movie_application.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_application.Film
import com.example.movie_application.MAIN
import com.example.movie_application.MOVIES
import com.example.movie_application.R
import com.example.movie_application.USER
import com.example.movie_application.USERS_LIB
import com.example.movie_application.adapter.FilmAdapter
import com.example.movie_application.databinding.FragmentFilterBinding
import com.google.android.material.textfield.TextInputEditText

class FilterFragment : Fragment(), FilmAdapter.OnAddButtonClickListener{
    lateinit var binding: FragmentFilterBinding
    private lateinit var adapter: FilmAdapter
    private lateinit var films: List<Film>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        films = MOVIES
        val searching: TextInputEditText = view.findViewById(R.id.search_text)
        val list: RecyclerView = view.findViewById(R.id.list)
        val actionButton: Button = view.findViewById(R.id.action_button)
        val showAllButton: Button = view.findViewById(R.id.show_all_button)
        val fantasyButton: Button = view.findViewById(R.id.fantasy_button)
        val horrorButton: Button = view.findViewById(R.id.horror_button)

        adapter = FilmAdapter(films)
        adapter.setOnAddButtonClickListener(this)
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(requireContext())

        fantasyButton.setOnClickListener { filterFilmsByCategory("fantasy") }
        horrorButton.setOnClickListener { filterFilmsByCategory("horror") }
        actionButton.setOnClickListener { filterFilmsByCategory("action") }
        showAllButton.setOnClickListener { showAllFilms() }

        if(USER.name != "***"){
            binding.bNavMain.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.button_to_home -> {
                        MAIN.navController.navigate(R.id.action_filterFragment_to_mainFragment)
                    }
                    R.id.button_to_profile -> {
                        MAIN.navController.navigate(R.id.action_filterFragment_to_profileFragment)
                    }
                }
                true
            }
        }
        else{
            binding.bNavMain.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.button_to_home -> {
                        MAIN.navController.navigate(R.id.action_filterFragment_to_mainFragment)
                    }
                    R.id.button_to_profile -> {
                        MAIN.navController.navigate(R.id.action_filterFragment_to_authFragment)
                    }
                }
                true
            }
        }


        searching.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val searchTerm = s.toString().trim()
                if (searchTerm.isNotEmpty()) {
                    searchFilmsByName(searchTerm)
                } else {
                    showAllFilms()
                }
            }
        })
    }

    override fun onAddButtonClick(position: Int) {
        val filmToAdd = films[position]

        val currentUser = USER

        val userIndex = USERS_LIB.indexOfFirst { it.first == currentUser }

        if (userIndex != -1) {
            val userLibrary = USERS_LIB[userIndex].second
            val filmExists = userLibrary.any { it.title == filmToAdd.title }

            if (filmExists) {
                Toast.makeText(requireContext(), "Фильм уже добавлен в библиотеку ${currentUser.name}", Toast.LENGTH_LONG).show()
            } else {
                val updatedFilms = userLibrary.toMutableList().apply { add(filmToAdd) }
                USERS_LIB = USERS_LIB.toMutableList().apply { set(userIndex, Pair(currentUser, updatedFilms)) }
                Toast.makeText(requireContext(), "Фильм добавлен в библиотеку ${currentUser.name}", Toast.LENGTH_LONG).show()
            }
        } else {
            USERS_LIB += Pair(currentUser, listOf(filmToAdd))
            Toast.makeText(requireContext(), "Фильм добавлен в библиотеку ${currentUser.name}", Toast.LENGTH_LONG).show()
        }
    }


    private fun searchFilmsByName(name: String) {
        val searchResult = films.filter { it.title.contains(name, ignoreCase = true) }
        updateRecyclerView(searchResult)
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
