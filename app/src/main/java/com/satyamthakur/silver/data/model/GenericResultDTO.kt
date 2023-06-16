package com.satyamthakur.silver.data.model

import com.google.gson.annotations.SerializedName

data class GenericResultDTO<T>(

    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: T
)
