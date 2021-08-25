package com.bill.android.themoviedb.repository

import com.bill.android.themoviedb.model.Movie
import com.bill.android.themoviedb.services.MovieResult
import com.bill.android.themoviedb.services.MovieServiceEndpoint
import com.bill.android.themoviedb.services.MovieStore

class MoviesRepository() {

    suspend fun fetchPlayingNowMovies(): MovieResult<List<Movie>> {
        val result = MovieStore.loadAndDecode(MovieServiceEndpoint.NOWPLAYING)
        return MovieResult(result.data?.results, result.error)
    }

    suspend fun fetchTopRatedMovies(): MovieResult<List<Movie>> {
        val result = MovieStore.loadAndDecode(MovieServiceEndpoint.TOPRATED)
        return MovieResult(result.data?.results, result.error)
    }

    suspend fun fetchUpcomingMovies(): MovieResult<List<Movie>> {
        val result = MovieStore.loadAndDecode(MovieServiceEndpoint.UPCOMING)
        return MovieResult(result.data?.results, result.error)
    }

    suspend fun fetchPopularMovies(): MovieResult<List<Movie>> {
        val result = MovieStore.loadAndDecode(MovieServiceEndpoint.POPULAR)
        return MovieResult(result.data?.results, result.error)
    }
}