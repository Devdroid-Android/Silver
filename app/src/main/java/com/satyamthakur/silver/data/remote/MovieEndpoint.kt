package com.satyamthakur.silver.data.remote

import com.satyamthakur.silver.data.model.cast.ActorDTO
import com.satyamthakur.silver.data.model.GenericResultDTO
import com.satyamthakur.silver.data.model.MovieDTO
import com.satyamthakur.silver.data.model.cast.CreditsDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieEndpoint {

    @GET("movie/now_playing")
    suspend fun getNowShowingMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US"
    ): GenericResultDTO<List<MovieDTO>>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US"
    ): GenericResultDTO<List<MovieDTO>>

    @GET("person/{id}")
    suspend fun getCastMovie(
        @Path(value = "id") id: Int,
        @Query("language") language: String = "en-Us",

    ): ActorDTO

    @GET("movie/{movie_id}")
    suspend fun getCreditsMovie(
        @Path(value = "movie_id") movieId: Int
    ): CreditsDTO


}
