package com.satyamthakur.silver.ui.screen.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.Navigator
import com.satyamthakur.silver.R
import com.satyamthakur.silver.domain.model.Movie
import com.satyamthakur.silver.ui.screen.dashboard.component.HorizontalMovies
import com.satyamthakur.silver.ui.screen.dashboard.component.SectionSeparator
import com.satyamthakur.silver.ui.screen.dashboard.component.VerticalMovies
import com.satyamthakur.silver.utility.Resource
import com.satyamthakur.silver.utility.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onMovieClicked: (Movie) -> Unit,
    nowShowingMovies: Resource<List<Movie>>,
    onNowShowingMoviesRetry: () -> Unit,
    onSeeMoreNowShowingMovies: () -> Unit,
    popularMovies: Resource<List<Movie>>,
    onSeeMorePopularMoviesClicked: () -> Unit,
    onPopularMoviesRetry: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
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
    ) {
        Column(modifier = modifier) {
            if (nowShowingMovies is Resource.Loading && popularMovies is Resource.Loading) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        12.dp,
                        Alignment.CenterVertically
                    ),
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = stringResource(R.string.getting_the_movies_for_you))
                    LinearProgressIndicator()
                }
            } else {
                Column(modifier = Modifier.fillMaxSize()) {
                    SectionSeparator(
                        sectionTitle = stringResource(id = R.string.now_showing),
                        onSeeMoreClick = onSeeMoreNowShowingMovies
                    )
                    when (nowShowingMovies) {
                        is Resource.Error -> {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(
                                    8.dp,
                                    Alignment.CenterVertically
                                ),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(
                                    text = nowShowingMovies.message
                                        ?: stringResource(id = R.string.unknown_error),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 24.dp)
                                )
                                Button(onClick = onNowShowingMoviesRetry) {
                                    Text(text = stringResource(id = R.string.retry))
                                }
                            }
                        }

                        Resource.Loading -> {
                            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                        }

                        Resource.None -> Unit
                        is Resource.Success -> {
                            HorizontalMovies(
                                movies = nowShowingMovies.data ?: emptyList(),
                                onMovieClicked = {
                                    navController.currentBackStackEntry?.savedStateHandle?.set(
                                        key = "movie",
                                        value = it
                                    )
                                    navController.navigate(Screen.MovieScreen.route)
                                }
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
                    SectionSeparator(
                        sectionTitle = stringResource(R.string.popular),
                        onSeeMoreClick = onSeeMorePopularMoviesClicked
                    )
                    when (popularMovies) {
                        is Resource.Error -> {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(
                                    8.dp,
                                    Alignment.CenterVertically
                                ),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()
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

                        Resource.Loading -> {
                            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                        }

                        Resource.None -> Unit
                        is Resource.Success -> {
                            VerticalMovies(
                                movies = popularMovies.data ?: emptyList(),
                                onMovieClicked = onMovieClicked
                            )
                        }
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewDashboardScreen() {
//    val popularMovies = remember {
//        mutableStateOf(Resource.Success(PopularMovies))
//    }
//    SilverTheme {
//        Surface {
//            DashboardScreen(
//                onMovieClicked = {},
//                nowShowingMovies = popularMovies.value,
//                onSeeMoreNowShowingMovies = {},
//                onNowShowingMoviesRetry = {},
//                popularMovies = popularMovies.value,
//                onSeeMorePopularMoviesClicked = {},
//                onPopularMoviesRetry = {},
//            )
//        }
//    }
//}
