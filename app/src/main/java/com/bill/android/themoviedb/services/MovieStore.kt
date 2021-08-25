package com.bill.android.themoviedb.services

import com.bill.android.themoviedb.model.MovieResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MovieStore {

    companion object {
        private const val apiKey = "9d29b0c3e1207f0fabc8abfb63ec07cd"
        private const val baseUrl = "https://api.themoviedb.org/3/"

        private val instance: MovieService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(MovieService::class.java)
        }

        suspend fun loadAndDecode(
            endpoint: MovieServiceEndpoint
        ): MovieResult<MovieResponse> {
            return try {
                val response: Response<MovieResponse> = instance.fetchMovies(endpoint.apiPath, apiKey)
                if (response.isSuccessful) {
                    val body = response.body()
                    return if (body != null) {
                        MovieResult.success(body)
                    } else {
                        MovieResult.error(Exception(MovieErrorMessage.NODATA.message))
                    }
                }
                MovieResult.error(Exception(MovieErrorMessage.APIERROR.message))
            } catch (e: Exception) {
                MovieResult.error(e)
            }

        }
    }
}