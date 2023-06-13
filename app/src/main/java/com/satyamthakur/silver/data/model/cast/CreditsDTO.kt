package com.satyamthakur.silver.data.model.cast

import com.google.gson.annotations.SerializedName

data class CreditsDTO(
    @SerializedName("cast")
    val cast: List<CastDTO>,

    @SerializedName("crew")
    val crew: List<CrewDTO>,

    @SerializedName("id")
    val movieId: Int
)