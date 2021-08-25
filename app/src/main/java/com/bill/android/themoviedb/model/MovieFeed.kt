package com.bill.android.themoviedb.model

enum class MovieListType {
    NOWPLAYING, TOPRATED, UPCOMING, POPULAR
}

data class MovieList(
    val type: MovieListType,
    val name: String,
    val movies: List<Movie>
)