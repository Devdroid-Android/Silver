package com.satyamthakur.silver.sl

import com.satyamthakur.silver.data.repository.IMovieRepository
import com.satyamthakur.silver.data.repository.MovieRepository
import com.satyamthakur.silver.utility.ErrorParser
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single<IMovieRepository> {
        val context = androidContext()
        MovieRepository(
            movieApiService = get(),
            errorParser = ErrorParser(context)
        )
    }
}