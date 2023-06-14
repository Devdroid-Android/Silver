package com.satyamthakur.silver.utility

sealed class Screen(val route: String) {
    object Dashboard : Screen("dashboard")
    object SeeMoreNowShowingMovies : Screen("now_showing")
    object SeeMorePopularMovies : Screen("popular")
    object MovieScreen: Screen("movie_screen")


}
