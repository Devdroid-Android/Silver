package com.satyamthakur.silver.sl

import com.satyamthakur.silver.data.remote.MovieEndpoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        HttpLoggingInterceptor()
            .setLevel(
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.HEADERS
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            )
    } withOptions {
        createdAtStart()
    }

    single {
        OkHttpClient.Builder()
//            .addInterceptor { chain ->
//                chain.proceed(
//                    Request.Builder()
//                        .url(chain.request().url)
//                        .addHeader(
//                            name = "Authorization",
//                            value = "Bearer ${System.getProperties().getProperty("apiKey", "")}"
//                        )
//                        .build()
//                )
//            }
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    } withOptions {
        createdAtStart()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .validateEagerly(true)
            .build()
    } withOptions {
        createdAtStart()
    }

    single<MovieEndpoint> {
        val retrofit = get<Retrofit>()
        retrofit.create(MovieEndpoint::class.java)
    } withOptions {
        createdAtStart()
    }
}