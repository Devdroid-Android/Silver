package com.satyamthakur.silver.domain.mapper

import com.satyamthakur.silver.data.model.MovieDTO
import com.satyamthakur.silver.data.model.cast.ActorDTO
import com.satyamthakur.silver.data.model.cast.CastDTO
import com.satyamthakur.silver.data.model.cast.CreditsDTO
import com.satyamthakur.silver.data.model.cast.CrewDTO
import com.satyamthakur.silver.domain.model.Movie
import com.satyamthakur.silver.domain.model.cast.Actor
import com.satyamthakur.silver.domain.model.cast.Cast
import com.satyamthakur.silver.domain.model.cast.Credits
import com.satyamthakur.silver.domain.model.cast.Crew

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

fun ActorDTO.asActor() = Actor(
    adult = adult,
    also_known_as = alsoKnownAs,
    biography = biography,
    birthday = birthday,
    deathday = deathDay,
    gender = gender,
    homepage = homepage,
    id = id,
    imdb_id = imdbId,
    known_for_department = knownForDepartment,
    name = actorName,
    place_of_birth = placeOfBirth,
    popularity = popularity,
    profile_path = profilePath
)

fun CrewDTO.asCrew() = Crew(
    adult = adult,
    creditId = creditId,
    department = department,
    gender = gender,
    crewId = crewId,
    job = job,
    knownForDepartment = knownForDepartment,
    name = name,
    originalName = originalName,
    popularity = popularity,
    profilePath = profilePath
)

fun CastDTO.asCast() = Cast(
    adult = adult,
    castId = castId,
    character = character,
    creditId = creditId,
    gender = gender,
    actorId = actorId,
    knownForDepartment = knownForDepartment,
    name = name,
    order = order,
    originalName = originalName,
    popularity = popularity,
    profilePath = profilePath
)

fun CreditsDTO.asCredits(): Credits {
    val listCrew = mutableListOf<Crew>()
    val listCast = mutableListOf<Cast>()

    crew.forEach {
        listCrew.add(it.asCrew())
    }

    cast.forEach {
        listCast.add(it.asCast())
    }

    return Credits(
        cast = listCast,
        crew = listCrew,
        movieId = movieId
    )
}
