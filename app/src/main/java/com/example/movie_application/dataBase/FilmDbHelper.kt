package com.example.movie_application.DataBase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.movie_application.Film


class FilmDbHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, "app", factory, 1)  {

        override fun onCreate(db: SQLiteDatabase?) {
            val queryFilms = "CREATE TABLE films(id INTEGER PRIMARY KEY, title TEXT UNIQUE, description TEXT, rating REAL, category TEXT)"
            val queryUserLibrary = "CREATE TABLE user_library(id INTEGER PRIMARY KEY, user_name TEXT, film_name TEXT, FOREIGN KEY(user_name) REFERENCES users(name), FOREIGN KEY(film_name) REFERENCES films(name), UNIQUE(user_name, film_name))"

            db?.execSQL(queryFilms)
            db?.execSQL(queryUserLibrary)
        }


        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("DROP TABLE IF EXISTS films")
            db?.execSQL("DROP TABLE IF EXISTS user_library")
            onCreate(db)
        }

        @SuppressLint("Range")
        fun getAllFilms(): List<Film> {
            val db = readableDatabase
            val films = mutableListOf<Film>()
            val query = "SELECT * FROM films"
            val cursor = db.rawQuery(query, null)
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    val title = cursor.getString(cursor.getColumnIndex("title"))
                    val description = cursor.getString(cursor.getColumnIndex("description"))
                    val rating = cursor.getDouble(cursor.getColumnIndex("rating"))
                    val category = cursor.getString(cursor.getColumnIndex("category"))
                    films.add(Film(id, title, description, rating, category))
                } while (cursor.moveToNext())
            }
            cursor.close()
            db.close()
            return films.toList()
        }

        @SuppressLint("Range")
        fun getFilmsByCategory(category: String): List<Film> {
            val films = mutableListOf<Film>()
            val query = "SELECT * FROM films WHERE category = ?"
            val db = readableDatabase
            val cursor = db.rawQuery(query, arrayOf(category))
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    val title = cursor.getString(cursor.getColumnIndex("title"))
                    val description = cursor.getString(cursor.getColumnIndex("description"))
                    val rating = cursor.getDouble(cursor.getColumnIndex("rating"))
                    val filmCategory = cursor.getString(cursor.getColumnIndex("category"))
                    films.add(Film(id, title, description, rating, filmCategory))
                } while (cursor.moveToNext())
            }
            cursor.close()
            db.close()
            return films
        }

        @SuppressLint("Range")
        fun getFilm(title: String): Film? {
            val query = "SELECT * FROM films WHERE title = ?"
            val db = readableDatabase
            val cursor = db.rawQuery(query, arrayOf(title))
            var film: Film? = null
            if (cursor.moveToFirst()) {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val description = cursor.getString(cursor.getColumnIndex("description"))
                val rating = cursor.getDouble(cursor.getColumnIndex("rating"))
                val category = cursor.getString(cursor.getColumnIndex("category"))
                film = Film(id, title, description, rating, category)
            }
            cursor.close()
            db.close()
            return film
        }

        fun addFilm(film: Film) {
            val values = ContentValues().apply {
                put("title", film.title)
                put("description", film.description)
                put("rating", film.rating)
                put("category", film.category)
            }

            val db = writableDatabase
            db.use {
                db.insert("films", null, values)
            }
        }

        fun addUserLibraryItem(userName: String, filmName: String) {
            val values = ContentValues().apply {
                put("user_name", userName)
                put("film_name", filmName)
            }

            val db = writableDatabase
            db.insert("user_library", null, values)
            db.close()
        }
}