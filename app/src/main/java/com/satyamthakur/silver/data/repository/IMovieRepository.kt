package com.satyamthakur.silver.data.repository

import com.satyamthakur.silver.domain.model.Movie
import com.satyamthakur.silver.domain.model.cast.Actor
import com.satyamthakur.silver.domain.model.cast.Credits
import com.satyamthakur.silver.utility.Resource
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getNowShowingMovies(): Flow<Resource<List<Movie>>>
    fun getPopularMovies(): Flow<Resource<List<Movie>>>
    fun getActor(actorId: Int): Flow<Resource<Actor>>
    fun getCredits(movieId: Int): Flow<Resource<Credits>>
}

