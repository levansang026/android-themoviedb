package com.bill.android.themoviedb.services

import com.bill.android.themoviedb.model.MovieResponse
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

data class MovieResult<out T>(val data: T?, val error: Throwable?) {

    companion object {
        fun <T> success(data: T?): MovieResult<T> {
            return MovieResult(data, null)
        }

        fun <T> error(error: Throwable): MovieResult<T> {
            return MovieResult(null, error)
        }
    }
}

enum class MovieServiceEndpoint() {
    NOWPLAYING,
    TOPRATED,
    UPCOMING,
    POPULAR;

    val apiPath: String
        get() {
            return when (this) {
                NOWPLAYING -> "now_playing"
                TOPRATED -> "top_rated"
                UPCOMING -> "upcoming"
                POPULAR -> "popular"
            }
        }
}

enum class MovieErrorMessage(val message: String) {
    APIERROR("Failed to fetch data"),
    INVALIDENPOINT("Invalid endpoint"),
    INVALIDRESPONSE("Invalid response"),
    NODATA("NODATA"),
    SERIALIZATIONERROR("Serialization error")
}

interface MovieService {

    @GET("movie/{listType}")
    suspend fun fetchMovies(
        @Path("listType") path: String,
        @Query("api_key") api: String
    ): Response<MovieResponse>
}