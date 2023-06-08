package com.satyamthakur.silver.ui.screen.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satyamthakur.silver.data.repository.IMovieRepository
import com.satyamthakur.silver.domain.model.Movie
import com.satyamthakur.silver.domain.model.cast.Actor
import com.satyamthakur.silver.domain.model.cast.Credits
import com.satyamthakur.silver.utility.Resource
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class DashboardViewModel(
    private val repository: IMovieRepository,
) : ViewModel() {

    val popularMovies: StateFlow<Resource<List<Movie>>> = repository
        .getPopularMovies()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(15000),
            initialValue = Resource.None
        )

    val nowShowingMovies: StateFlow<Resource<List<Movie>>> = repository
        .getNowShowingMovies()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(15000),
            initialValue = Resource.None
        )

    fun filmDetails(movieId: Int): StateFlow<Resource<Credits>> = repository
        .getCredits(movieId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(15000),
            initialValue = Resource.None
        )

    fun showActorInfo(actorId: Int): StateFlow<Resource<Actor>> = repository
        .getActor(actorId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(15000),
            initialValue = Resource.None
        )

}
