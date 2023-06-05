package com.satyamthakur.silver.data.remote

import com.satyamthakur.silver.data.model.GenericResultDTO
import com.satyamthakur.silver.data.model.MovieDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieEndpoint {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US"
    ): GenericResultDTO<List<MovieDTO>>

}