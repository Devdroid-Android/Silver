package com.satyamthakur.silver.ui.screen.dashboard.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.satyamthakur.silver.domain.model.Movie
import com.satyamthakur.silver.ui.component.HorizontalMovieItem

@Composable
fun HorizontalMovies(
    movies: List<Movie>,
    onMovieClicked: (Movie) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            horizontal = 24.dp
        ),
        modifier = Modifier
            .padding(top = 16.dp)
    ) {
        items(
            items = movies,
            key = { movie ->
                movie.id
            }
        ) { movie: Movie ->
            HorizontalMovieItem(
                movie = movie,
                onCLicked = onMovieClicked
            )
        }
    }
}
