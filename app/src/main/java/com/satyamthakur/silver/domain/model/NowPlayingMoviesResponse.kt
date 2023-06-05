package com.satyamthakur.silver.domain.model

data class NowPlayingMoviesResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)
