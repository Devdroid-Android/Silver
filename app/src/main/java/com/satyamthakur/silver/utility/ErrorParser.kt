package com.satyamthakur.silver.utility

import android.content.Context
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.satyamthakur.silver.R
import com.satyamthakur.silver.data.model.ErrorDTO
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

class ErrorParser(
    private val context: Context
) {

    operator fun invoke(t: Throwable): String {
        if (t is IOException || t is UnknownHostException) {
            return context.getString(R.string.no_internet)
        }

        if (t is HttpException) {
            val errorResponse = t.response()?.errorBody()
            val gson = Gson()
            val errorAdapter: TypeAdapter<ErrorDTO> =
                gson.getAdapter(ErrorDTO::class.java)
            return try {
                val error = errorAdapter.fromJson(errorResponse?.string())
                "Error ${error.statusCode}: ${error.statusMessage}"
            } catch (e: Exception) {
                "Response failed to parse"
            }
        }

        return t.message ?: "Unknown Error"
    }
}