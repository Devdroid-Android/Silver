package com.satyamthakur.silver.data.model.cast
import com.google.gson.annotations.SerializedName

data class ActorDTO(
    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("also_known_as")
    val alsoKnownAs: List<String>,

    @SerializedName("biography")
    val biography: String,

    @SerializedName("birthday")
    val birthday: String,

    @SerializedName("deathday")
    val deathDay: String?,

    @SerializedName("gender")
    val gender: Int,

    @SerializedName("homepage")
    val homepage: String?,

    @SerializedName("id")
    val id: Int,

    @SerializedName("imdb_id")
    val imdbId: String,

    @SerializedName("known_for_department")
    val knownForDepartment: String,

    @SerializedName("name")
    val actorName: String,

    @SerializedName("place_of_birth")
    val placeOfBirth: String,

    @SerializedName("popularity")
    val popularity: Double,

    @SerializedName("profile_path")
    val profilePath: String,

)