package com.pranay.reflectionai

import android.app.Application
import com.pranay.reflectionai.api.MovieService
import com.pranay.reflectionai.api.RetrofitHelper
import com.pranay.reflectionai.db.MoviesDatabase
import com.pranay.reflectionai.repository.Repository
import retrofit2.create

class MovieApplication:Application() {

    lateinit var movieRepository: Repository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val database = MoviesDatabase.getDatabase(applicationContext)
        val moviesService = RetrofitHelper.getInstance().create(MovieService::class.java)
         movieRepository = Repository(moviesService,database,applicationContext)
    }

}