package com.satyamthakur.silver.ui.screen.dashboard.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.satyamthakur.silver.R
import com.satyamthakur.silver.domain.model.Movie
import com.satyamthakur.silver.ui.component.HorizontalMovieItem
import com.satyamthakur.silver.utility.Resource

@Composable
fun HorizontalMovies(
    moviesResult: Resource<List<Movie>>,
    onMovieClicked: (Movie) -> Unit,
    onRetry: () -> Unit,
) {
    when (moviesResult) {
        is Resource.Error ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    12.dp,
                    Alignment.CenterVertically
                ),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = moviesResult.message
                        ?: stringResource(id = R.string.unknown_error),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                )
                Button(onClick = onRetry) {
                    Text(text = stringResource(id = R.string.retry))
                }
            }

        Resource.Loading ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    12.dp,
                    Alignment.CenterVertically
                ),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(text = stringResource(R.string.getting_the_movies_for_you))
                LinearProgressIndicator()
            }

        Resource.None -> Unit
        is Resource.Success ->
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    horizontal = 24.dp
                ),
                modifier = Modifier
                    .padding(top = 16.dp)
            ) {
                items(
                    items = moviesResult.data ?: emptyList(),
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
}
