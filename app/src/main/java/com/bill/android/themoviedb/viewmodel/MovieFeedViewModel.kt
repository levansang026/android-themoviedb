package com.bill.android.themoviedb.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bill.android.themoviedb.model.MovieList
import com.bill.android.themoviedb.model.MovieListType
import com.bill.android.themoviedb.repository.MoviesRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieFeedViewModel(private val repository: MoviesRepository): ViewModel() {

    private val _movieLists = MutableLiveData<List<MovieList>>(
        listOf(
            MovieList(MovieListType.NOWPLAYING, "Now Playing", listOf()),
            MovieList(MovieListType.TOPRATED, "Top Rated", listOf()),
            MovieList(MovieListType.POPULAR, "Popular", listOf()),
            MovieList(MovieListType.UPCOMING, "Upcoming", listOf()),
        )
    )
    val movieLists: LiveData<List<MovieList>> = _movieLists

    init {
        getMovieFeed()
    }

    private fun getMovieFeed() {

        // Now Playing List
        viewModelScope.launch {
            try {
                val result = repository.fetchPlayingNowMovies()
                result.data?.let { response ->
                    _movieLists.value?.let { current ->
                        val list = listOf(
                            MovieList(MovieListType.NOWPLAYING, "Now Playing", response),
                            current.elementAt(1),
                            current.elementAt(2),
                            current.elementAt(3)
                        )
                        _movieLists.value = list
                    }
                }

                result.error?.let {
                    Log.e("MovieFeedViewModel", it.localizedMessage)
                }

            } catch (e: Exception) {
                Log.e("MovieFeedViewModel", e.toString())
            }
        }

        // Top Rated List
        viewModelScope.launch {
            try {
                val result = repository.fetchTopRatedMovies()
                result.data?.let { response ->
                    _movieLists.value?.let { current ->
                        val list = listOf(
                            current.elementAt(0),
                            MovieList(MovieListType.TOPRATED, "Top Rated", response),
                            current.elementAt(2),
                            current.elementAt(3)
                        )
                        _movieLists.value = list
                    }
                }

                result.error?.let {
                    Log.e("MovieFeedViewModel", it.localizedMessage)
                }

            } catch (e: Exception) {
                Log.e("MovieFeedViewModel", e.toString())
            }
        }

        // Popular List
        viewModelScope.launch {
            try {
                val result = repository.fetchPopularMovies()
                result.data?.let { response ->
                    _movieLists.value?.let { current ->
                        val list = listOf(
                            current.elementAt(0),
                            current.elementAt(1),
                            MovieList(MovieListType.POPULAR, "Popular", response),
                            current.elementAt(3)
                        )
                        _movieLists.value = list
                    }
                }

                result.error?.let {
                    Log.e("MovieFeedViewModel", it.localizedMessage)
                }

            } catch (e: Exception) {
                Log.e("MovieFeedViewModel", e.toString())
            }
        }

        // Upcoming List
        viewModelScope.launch {
            try {
                val result = repository.fetchUpcomingMovies()
                result.data?.let { response ->
                    _movieLists.value?.let { current ->
                        val list = listOf(
                            current.elementAt(0),
                            current.elementAt(1),
                            current.elementAt(2),
                            MovieList(MovieListType.UPCOMING, "Upcoming", response)
                        )
                        _movieLists.value = list
                    }
                }

                result.error?.let {
                    Log.e("MovieFeedViewModel", it.localizedMessage)
                }

            } catch (e: Exception) {
                Log.e("MovieFeedViewModel", e.toString())
            }
        }
    }
}