package com.bill.android.themoviedb.model

import com.google.gson.annotations.SerializedName
import java.net.URL

data class MovieResponse(
    @SerializedName("results") val results: List<Movie>
    )

data class Movie(
    val id: String,
    val title: String,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("poster_path") val posterPath: String?,
) {

    val backdropUrl = "https://image.tmdb.org/t/p/w500${backdropPath}"
    val posterUrl  = "https://image.tmdb.org/t/p/w500${posterPath}"
}