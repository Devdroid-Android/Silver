package com.satyamthakur.silver.ui.screen.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyamthakur.silver.data.repository.IMovieRepository
import com.satyamthakur.silver.domain.model.Movie
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

    init {
        getNowShowingMovies()
        getPopularMovies()
    }
}
