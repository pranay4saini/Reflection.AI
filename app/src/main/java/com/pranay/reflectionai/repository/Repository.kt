package com.pranay.reflectionai.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pranay.reflectionai.api.MovieService
import com.pranay.reflectionai.db.MoviesDatabase
import com.pranay.reflectionai.model.MoviesResponse
import com.pranay.reflectionai.utils.NetworkUtils


class Repository(
    private val retrofitService: MovieService,
    private val moviesDatabase: MoviesDatabase,
    private val applicationContext: Context
) {


    private val moviesLiveData = MutableLiveData<MoviesResponse>()

    val movies:LiveData<MoviesResponse>
    get() = moviesLiveData


    suspend fun getAllMovies(){
        if(NetworkUtils.isInternetAvailable(applicationContext)){

            val result = retrofitService.getAllMovies()
            if (result?.body() != null){
//                moviesDatabase.moviesDao().addMovies(result.body()!!.Movie_List)
                moviesLiveData.postValue(result.body()!!)

            }
        }else{

            val movies = moviesDatabase.moviesDao().getMovies()
            val moviesList = MoviesResponse(movies)
            moviesLiveData.postValue(moviesList)

        }


    }

}