package com.pranay.reflectionai.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pranay.reflectionai.model.Movie


@Dao
interface MoviesDao {

    @Insert
    suspend fun addMovies(movies: List<Movie>)



    @Query("SELECT * FROM movies")
    suspend fun getMovies() : List<Movie>


}