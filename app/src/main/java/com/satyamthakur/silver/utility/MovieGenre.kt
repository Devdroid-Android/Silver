package com.satyamthakur.silver.utility

enum class MovieGenre(
    val title: String
) {
    Action(title = "Action"),
    Adventure(title = "Adventure"),
    Animation(title = "Animation"),
    Comedy(title = "Comedy"),
    Crime(title = "Crime"),
    Documentary(title = "Documentary"),
    Drama(title = "Drama"),
    Family(title = "Family"),
    Fantasy(title = "Fantasy"),
    History(title = "History"),
    Horror(title = "Horror"),
    Music(title = "Music"),
    Mystery(title = "Mystery"),
    Romance(title = "Romance"),
    ScienceFiction(title = "Science Fiction"),
    TvMovie(title = "TV Movie"),
    Thriller(title = "Thriller"),
    War(title = "War"),
    Western(title = "Western")
}

fun getMovieGenreByGenreID(genreID: Int): MovieGenre {
    return when (genreID) {
        28 -> MovieGenre.Action
        12 -> MovieGenre.Adventure
        16 -> MovieGenre.Animation
        35 -> MovieGenre.Comedy
        80 -> MovieGenre.Crime
        99 -> MovieGenre.Documentary
        18 -> MovieGenre.Drama
        10751 -> MovieGenre.Family
        14 -> MovieGenre.Fantasy
        36 -> MovieGenre.History
        27 -> MovieGenre.Horror
        10402 -> MovieGenre.Music
        9648 -> MovieGenre.Mystery
        10749 -> MovieGenre.Romance
        878 -> MovieGenre.ScienceFiction
        10770 -> MovieGenre.TvMovie
        53 -> MovieGenre.Thriller
        10752 -> MovieGenre.War
        else -> MovieGenre.Western
    }
}
