package com.satyamthakur.silver.ui.screen.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import com.satyamthakur.silver.ui.theme.SilverTheme
import com.satyamthakur.silver.utility.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    popularMovies: Resource<List<Movie>>,
    onMovieClicked: (Movie) -> Unit,
    onSeeMorePopularMoviesClicked: () -> Unit
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Black, fontSize = 16.sp
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(
                    horizontal = 24.dp
                )
            ) {
                Text(
                    text = "Popular",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Black,
                        fontSize = 18.sp
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                AssistChip(
                    onClick = onSeeMorePopularMoviesClicked::invoke,
                    shape = RoundedCornerShape(100.dp),
                    label = {
                        Text(
                            text = "See More",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 12.sp,
                            ),
                        )
                    },
                    border = AssistChipDefaults.assistChipBorder(
                        borderColor = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.5F
                        ),
                        borderWidth = 1.dp
                    ),
                    colors = AssistChipDefaults.assistChipColors(
                        labelColor = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.5F
                        )
                    )
                )
            }
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
                                onCLicked = onMovieClicked::invoke
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
                popularMovies = popularMovies.value,
                onMovieClicked = {},
                onSeeMorePopularMoviesClicked = {},
            )
        }
    }
}