package com.satyamthakur.silver.ui.screen.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.satyamthakur.silver.R
import com.satyamthakur.silver.domain.model.Movie
import com.satyamthakur.silver.domain.model.PopularMovies
import com.satyamthakur.silver.ui.component.HorizontalMovieItem
import com.satyamthakur.silver.ui.component.VerticalMovieItem
import com.satyamthakur.silver.ui.screen.dashboard.component.SectionSeparator
import com.satyamthakur.silver.ui.theme.SilverTheme
import com.satyamthakur.silver.utility.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onMovieClicked: (Movie) -> Unit,
    popularMovies: Resource<List<Movie>>,
    onSeeMorePopularMoviesClicked: () -> Unit,
    airingMovies: Resource<List<Movie>>,
    onSeeMoreAiringMoviesClicked: () -> Unit
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Black,
                    fontSize = 16.sp
                )
            )
        })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            SectionSeparator(
                sectionTitle = stringResource(R.string.popular),
                onSeeMoreClick = onSeeMorePopularMoviesClicked
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    horizontal = 24.dp
                )
            ) {
                when (popularMovies) {
                    is Resource.Error -> Unit
                    Resource.Loading -> Unit
                    Resource.None -> Unit
                    is Resource.Success -> {
                        items(
                            items = popularMovies.data ?: emptyList(),
                            key = { movie ->
                                movie.id
                            },
                        ) { movie: Movie ->
                            HorizontalMovieItem(
                                movie = movie,
                                onCLicked = onMovieClicked
                            )
                        }
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
            )
            SectionSeparator(
                sectionTitle = stringResource(R.string.popular),
                onSeeMoreClick = onSeeMoreAiringMoviesClicked
            )
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 24.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                when (airingMovies) {
                    is Resource.Error -> Unit
                    Resource.Loading -> Unit
                    Resource.None -> Unit
                    is Resource.Success -> {
                        items(
                            items = airingMovies.data ?: emptyList(),
                            key = { movie ->
                                movie.id
                            }
                        ) { movie ->
                            VerticalMovieItem(
                                movie = movie,
                                onMovieClicked = onMovieClicked
                            )
                        }
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
                popularMovies = popularMovies.value,
                onSeeMorePopularMoviesClicked = {},
                airingMovies = popularMovies.value,
                onSeeMoreAiringMoviesClicked = {}
            )
        }
    }
}