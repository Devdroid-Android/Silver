package com.satyamthakur.silver.data.model

import com.google.gson.annotations.SerializedName

data class ErrorDTO(

    @SerializedName("status_code")
    val statusCode: Int,

    @SerializedName("status_message")
    val statusMessage: String,

    @SerializedName("success")
    val success: Boolean
)
