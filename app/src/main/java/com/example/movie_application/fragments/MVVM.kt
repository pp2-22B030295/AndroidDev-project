package com.example.movie_application.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_application.Film
import com.example.movie_application.USERS_LIB

class ProfileViewModel : ViewModel() {
    private val _currentUserFilms = MutableLiveData<List<Film>>()
    val currentUserFilms: LiveData<List<Film>> get() = _currentUserFilms

    fun loadCurrentUserFilms(userName: String) {
        val currentUserPair = USERS_LIB.find { it.first == userName }
        val currentUserFilms = currentUserPair?.second ?: emptyList()
        _currentUserFilms.value = currentUserFilms
    }
}