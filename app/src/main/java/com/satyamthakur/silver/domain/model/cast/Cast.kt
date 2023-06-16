package com.satyamthakur.silver.domain.model.cast

data class Cast(
    val adult: Boolean,
    val castId: Int,
    val character: String,
    val creditId: String,
    val gender: Int,
    val actorId: Int,
    val knownForDepartment: String,
    val name: String,
    val order: Int,
    val originalName: String,
    val popularity: Double,
    val profilePath: String?
)