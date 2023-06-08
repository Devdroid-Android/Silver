package com.satyamthakur.silver.data.repository

import com.satyamthakur.silver.data.model.MovieDTO
import com.satyamthakur.silver.data.remote.MovieEndpoint
import com.satyamthakur.silver.domain.mapper.asMovie
import com.satyamthakur.silver.domain.mapper.asActor
import com.satyamthakur.silver.domain.mapper.asCredits
import com.satyamthakur.silver.domain.model.Movie
import com.satyamthakur.silver.domain.model.cast.Actor
import com.satyamthakur.silver.domain.model.cast.Credits
import com.satyamthakur.silver.utility.ErrorParser
import com.satyamthakur.silver.utility.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class MovieRepository(
    private val movieApiService: MovieEndpoint,
    private val errorParser: ErrorParser,
    private val ioDispatcher: CoroutineContext = Dispatchers.IO
) : IMovieRepository {
    override fun getNowShowingMovies(): Flow<Resource<List<Movie>>> {
        return flow<Resource<List<Movie>>> {
            val apiResult = movieApiService.getNowShowingMovies()
            emit(Resource.Success(data = apiResult.results.map(MovieDTO::asMovie)))
        }.catch { t ->
            Timber.w(t)
            emit(
                Resource.Error(
                    data = null,
                    message = errorParser(t)
                )
            )
        }.onStart {
            emit(Resource.Loading)
        }.flowOn(ioDispatcher)
    }

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> {
        return flow<Resource<List<Movie>>> {
            val apiResult = movieApiService.getPopularMovies()
            emit(Resource.Success(data = apiResult.results.map(MovieDTO::asMovie)))
        }.catch { t ->
            Timber.w(t)
            emit(
                Resource.Error(
                    data = null,
                    message = errorParser(t)
                )
            )
        }.onStart {
            emit(Resource.Loading)
        }.flowOn(ioDispatcher)
    }

    override fun getActor(actorId: Int): Flow<Resource<Actor>> {
        return flow<Resource<Actor>> {
            val apiResult = movieApiService.getCastMovie(actorId)
            emit(
                Resource.Success(
                    data = apiResult.asActor()
                )
            )
        }.catch { t ->
            Timber.w(t)
            emit(
                Resource.Error(
                    data = null,
                    message = errorParser(t)
                )
            )
        }.onStart {
            emit(Resource.Loading)
        }.flowOn(ioDispatcher)
    }

    override fun getCredits(movieId: Int): Flow<Resource<Credits>> {
        return flow<Resource<Credits>> {
            val apiResult = movieApiService.getCreditsMovie(movieId)
            emit(
                Resource.Success(
                    data = apiResult.asCredits()
                )
            )
        }.catch { t ->
            Timber.w(t)
            emit(
                Resource.Error(
                    data = null,
                    message = errorParser(t)
                )
            )
        }.onStart {
            emit(Resource.Loading)
        }.flowOn(ioDispatcher)
    }
}
