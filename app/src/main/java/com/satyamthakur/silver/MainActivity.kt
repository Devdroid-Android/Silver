package com.satyamthakur.silver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.satyamthakur.silver.ui.screen.dashboard.DashboardScreen
import com.satyamthakur.silver.ui.screen.dashboard.viewmodel.DashboardViewModel
import com.satyamthakur.silver.ui.theme.SilverTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SilverTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val viewModel = koinViewModel<DashboardViewModel>()
                    val nowShowingMovies by viewModel.nowShowingMovies.collectAsStateWithLifecycle()
                    val popularMovies by viewModel.popularMovies.collectAsStateWithLifecycle()
                    DashboardScreen(
                        onMovieClicked = {},
                        nowShowingMovies = nowShowingMovies,
                        onNowShowingMoviesRetry = {},
                        onSeeMoreNowShowingMovies = {},
                        popularMovies = popularMovies,
                        onSeeMorePopularMoviesClicked = {},
                        onPopularMoviesRetry = {}
                    )
                }
            }
        }
    }
}
