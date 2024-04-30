package com.example.movie_application.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_application.Film
import com.example.movie_application.R

class FilmAdapter(var filmList: List<Film>) :
    RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = filmList[position]
        holder.filmTitle.text = film.title
        holder.filmDescription.text = film.description
        holder.filmRating.text = film.rating.toString()
        holder.filmCategory.text = film.category
    }

    fun setData(newList: List<Film>) {
        filmList = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val filmTitle: TextView = itemView.findViewById(R.id.film_title)
        val filmDescription: TextView = itemView.findViewById(R.id.film_description)
        val filmRating: TextView = itemView.findViewById(R.id.film_rating)
        val filmCategory: TextView = itemView.findViewById(R.id.film_category)
    }
}
