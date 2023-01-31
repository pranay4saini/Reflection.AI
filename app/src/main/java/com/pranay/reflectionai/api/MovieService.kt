package com.pranay.reflectionai.api


import com.pranay.reflectionai.model.Movie
import com.pranay.reflectionai.model.MoviesResponse

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory

interface MovieService {

    @GET("1.json")
    suspend fun getAllMovies(): Response<MoviesResponse>

    @GET("2.json")
    suspend fun getAllMovie2(): Response<MoviesResponse>


}