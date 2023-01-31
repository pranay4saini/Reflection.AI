package com.pranay.reflectionai.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    val IMDBID: String,
    val Cast: String?,
    val Genres: String?,
    @SerializedName("Movie Poster")
    val MoviePoster: String?,
    val Rating: String?,
    val Runtime: String?,
    val Title: String?,
    val Year: String?

):Serializable