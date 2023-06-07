package com.satyamthakur.silver.domain.mapper

import com.satyamthakur.silver.data.model.MovieDTO
import com.satyamthakur.silver.domain.model.Movie

fun MovieDTO.asMovie() = Movie(
    adult = adult,
    backdropPath = backdropPath,
    genresID = genresID,
    id = id,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)
