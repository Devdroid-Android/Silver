package com.satyamthakur.silver.ui.screen.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyamthakur.silver.data.repository.IMovieRepository
import com.satyamthakur.silver.domain.model.Movie
import com.satyamthakur.silver.domain.model.cast.Actor
import com.satyamthakur.silver.domain.model.cast.Credits
import com.satyamthakur.silver.utility.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repository: IMovieRepository
) : ViewModel() {

    private val _nowShowingMovies: MutableStateFlow<Resource<List<Movie>>> =
        MutableStateFlow(Resource.None)
    val nowShowingMovies: StateFlow<Resource<List<Movie>>> = _nowShowingMovies.asStateFlow()

    private val _popularMovies: MutableStateFlow<Resource<List<Movie>>> =
        MutableStateFlow(Resource.None)
    val popularMovies: StateFlow<Resource<List<Movie>>> = _popularMovies.asStateFlow()

    private val _credits: MutableStateFlow<Resource<Credits>> = MutableStateFlow(Resource.None)
    var credits = _credits.asStateFlow()

    private val _actors: MutableStateFlow<Resource<Actor>> = MutableStateFlow(Resource.None)
    var actor = _actors.asStateFlow()

    fun getNowShowingMovies() = viewModelScope.launch {
        repository.getNowShowingMovies().collectLatest { nowShowingMoviesResult ->
            _nowShowingMovies.update { nowShowingMoviesResult }
        }
    }

    fun getPopularMovies() = viewModelScope.launch {
        repository.getPopularMovies().collectLatest { popularMoviesResult ->
            _popularMovies.update { popularMoviesResult }
        }
    }

    fun getCreditsData(movieId: Int) = viewModelScope.launch {
        repository.getCredits(movieId).collectLatest { creditsResult ->
            _credits.update { creditsResult }
        }
    }

    fun getActorData(actorId: Int) = viewModelScope.launch {
        repository.getActor(actorId).collectLatest {actorResult ->
            _actors.update { actorResult }
        }
    }

    init {
        getNowShowingMovies()
        getPopularMovies()
    }
}
