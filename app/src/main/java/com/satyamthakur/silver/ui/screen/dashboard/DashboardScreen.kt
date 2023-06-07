package com.satyamthakur.silver.ui.screen.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.satyamthakur.silver.R
import com.satyamthakur.silver.domain.model.Movie
import com.satyamthakur.silver.domain.model.PopularMovies
import com.satyamthakur.silver.ui.component.VerticalMovieItem
import com.satyamthakur.silver.ui.screen.dashboard.component.HorizontalMovies
import com.satyamthakur.silver.ui.screen.dashboard.component.SectionSeparator
import com.satyamthakur.silver.ui.theme.SilverTheme
import com.satyamthakur.silver.utility.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onMovieClicked: (Movie) -> Unit,
    nowShowingMovies: Resource<List<Movie>>,
    onNowShowingMoviesRetry: () -> Unit,
    onSeeMoreNowShowingMovies: () -> Unit,
    popularMovies: Resource<List<Movie>>,
    onSeeMorePopularMoviesClicked: () -> Unit,
    onPopularMoviesRetry: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Black,
                        fontSize = 16.sp
                    )
                )
            })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .requiredHeightIn()
            ) {
                Column {
                    SectionSeparator(
                        sectionTitle = stringResource(R.string.now_showing),
                        onSeeMoreClick = onSeeMoreNowShowingMovies
                    )
                    HorizontalMovies(
                        moviesResult = nowShowingMovies,
                        onMovieClicked = onMovieClicked,
                        onRetry = onNowShowingMoviesRetry
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Column {
                    SectionSeparator(
                        sectionTitle = stringResource(R.string.popular),
                        onSeeMoreClick = onSeeMorePopularMoviesClicked
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp)
                    )
                    Box(modifier = Modifier.fillMaxSize()) {
                        LazyColumn(
                            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            when (popularMovies) {
                                is Resource.Error ->
                                    item {
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
                                                text = popularMovies.message
                                                    ?: stringResource(id = R.string.unknown_error),
                                                textAlign = TextAlign.Center,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 24.dp)
                                            )
                                            Button(onClick = onPopularMoviesRetry) {
                                                Text(text = stringResource(id = R.string.retry))
                                            }
                                        }
                                    }

                                Resource.Loading ->
                                    item {
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
                                    }


                                Resource.None -> Unit
                                is Resource.Success ->
                                    items(
                                        items = popularMovies.data ?: emptyList(),
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
            }
        }
    }
}

@Preview
@Composable
fun PreviewDashboardScreen() {
    val popularMovies = remember {
        mutableStateOf(Resource.Success(PopularMovies))
    }
    SilverTheme {
        Surface {
            DashboardScreen(
                onMovieClicked = {},
                nowShowingMovies = popularMovies.value,
                onSeeMoreNowShowingMovies = {},
                onNowShowingMoviesRetry = {},
                popularMovies = popularMovies.value,
                onSeeMorePopularMoviesClicked = {},
                onPopularMoviesRetry = {}
            )
        }
    }
}
