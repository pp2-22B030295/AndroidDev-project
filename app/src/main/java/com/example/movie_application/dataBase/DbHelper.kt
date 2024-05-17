package com.example.movie_application.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.movie_application.User

class DbHelper(val context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, "app", factory, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        createTables(db)
    }

    private fun createTables(db: SQLiteDatabase?) {
        val queryUsers = "CREATE TABLE users(id INTEGER PRIMARY KEY, name TEXT UNIQUE, password TEXT)"

        db?.execSQL(queryUsers)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun addUser(user: User) {
        val values = ContentValues().apply {
            put("name", user.name)
            put("password", user.password)
        }

        val db = writableDatabase
        db.insert("users", null, values)
        db.close()
    }

    fun getUser(name: String, password: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM users WHERE name = ? AND password = ?"
        val cursor = db.rawQuery(query, arrayOf(name, password))
        val result = cursor.moveToFirst()
        cursor.close()
        db.close()
        return result
    }

}