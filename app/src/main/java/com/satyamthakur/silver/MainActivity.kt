package com.satyamthakur.silver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.satyamthakur.silver.domain.model.Movie
import com.satyamthakur.silver.ui.screen.dashboard.DashboardScreen
import com.satyamthakur.silver.ui.screen.dashboard.movie.MovieScreen
import com.satyamthakur.silver.ui.screen.dashboard.viewmodel.DashboardViewModel
import com.satyamthakur.silver.ui.theme.SilverTheme
import com.satyamthakur.silver.utility.Screen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()

            val viewModel = koinViewModel<DashboardViewModel>()
            val nowShowingMovies by viewModel.nowShowingMovies.collectAsStateWithLifecycle()
            val popularMovies by viewModel.popularMovies.collectAsStateWithLifecycle()

            SilverTheme {
                Surface {
                    Scaffold(

                    ) { paddingValues ->
                        NavHost(
                            navController = navController,
                            startDestination = Screen.Dashboard.route
                        ) {
                            composable(route = Screen.Dashboard.route) {

                                DashboardScreen(
                                    onMovieClicked = {},
                                    onSeeMoreNowShowingMovies = {},
                                    nowShowingMovies = nowShowingMovies,
                                    onNowShowingMoviesRetry = viewModel::getNowShowingMovies,
                                    onSeeMorePopularMoviesClicked = {},
                                    popularMovies = popularMovies,
                                    onPopularMoviesRetry = viewModel::getPopularMovies,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(paddingValues),
                                    navController = navController
                                )
                            }
                            composable(route = Screen.MovieScreen.route){
                                val movieItem = navController.previousBackStackEntry?.savedStateHandle?.get<Movie>("movie")
                                MovieScreen(movie = movieItem!!, navController = navController)
                            }
                        }
                        BackHandler(true) {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}

