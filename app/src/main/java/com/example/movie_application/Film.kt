package com.example.movie_application

class Film(
    val id: Int,
    val title: String,
    val description: String,
    val rating: Double,
    val category: String,
){}

class User(
    val name: String,
    val password: String,
){}
/**
@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val name: String,
    val password: String
)

@Entity(tableName = "films")
data class Film(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val description: String,
    val rating: Double,
    val category: String
)

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE name = :name AND password = :password")
    fun getUser(name: String, password: String): User?

    @Insert
    fun addUser(user: User)
}

@Dao
interface FilmDao {
    @Query("SELECT * FROM films")
    fun getAllFilms(): List<Film>

    @Insert
    fun addFilm(film: Film)
}

@Database(entities = [User::class, Film::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun filmDao(): FilmDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "app-database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
**/

