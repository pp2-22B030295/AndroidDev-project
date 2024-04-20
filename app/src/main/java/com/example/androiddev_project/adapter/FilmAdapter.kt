package com.example.androiddev_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddev_project.Film
import com.example.androiddev_project.databinding.ItemFilmBinding

class FilmAdapter : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {
    private val filmList: ArrayList<Film> = ArrayList()

    fun setData(films: ArrayList<Film>){
        filmList.clear()
        filmList.addAll(films)

        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFilmBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = filmList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filmList[position])
    }

    inner class ViewHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(film: Film){
                with(binding){
                    filmImage.setImageResource(film.poster)
                    filmTitle.text = film.title
                    filmDescription.text = film.description
                    filmRating.text = film.rating.toString()
                }
            }

        }

}