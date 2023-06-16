package com.satyamthakur.silver.domain.model.cast

data class Credits(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val movieId: Int
)
