package com.satyamthakur.silver.data.repository

import com.satyamthakur.silver.domain.model.Movie
import com.satyamthakur.silver.utility.Resource
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getNowShowingMovies(): Flow<Resource<List<Movie>>>
    fun getPopularMovies(): Flow<Resource<List<Movie>>>
}
