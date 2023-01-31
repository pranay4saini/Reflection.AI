package com.pranay.reflectionai.model

import com.google.gson.annotations.SerializedName


data class MoviesResponse(

    @SerializedName("Movie List")
    val Movie_List: List<Movie>
)