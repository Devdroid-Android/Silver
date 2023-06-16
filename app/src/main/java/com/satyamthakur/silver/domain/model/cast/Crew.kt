package com.satyamthakur.silver.domain.model.cast

import com.google.gson.annotations.SerializedName

data class Crew(
    val adult: Boolean,
    val creditId: String,
    val department: String,
    val gender: Int,
    val crewId: Int,
    val job: String,
    val knownForDepartment: String,
    val name: String,
    val originalName: String,
    val popularity: Double,
    val profilePath: String?
)
