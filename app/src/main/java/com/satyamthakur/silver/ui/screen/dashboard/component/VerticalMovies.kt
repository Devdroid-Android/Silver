package com.satyamthakur.silver.ui.screen.dashboard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.satyamthakur.silver.domain.model.Movie
import com.satyamthakur.silver.ui.component.VerticalMovieItem

@Composable
fun VerticalMovies(
    movies: List<Movie>,
    onMovieClicked: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.matchParentSize()
        ) {
            items(
                items = movies,
                key = { movie ->
                    movie.id
                }
            ) { movie: Movie ->
                VerticalMovieItem(
                    movie = movie,
                    onMovieClicked = onMovieClicked
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.background,
                            Color.Transparent
                        )
                    )
                )
        )
    }
}
