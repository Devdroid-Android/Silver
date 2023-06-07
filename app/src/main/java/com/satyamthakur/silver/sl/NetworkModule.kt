package com.satyamthakur.silver.sl

import com.satyamthakur.silver.R
import com.satyamthakur.silver.data.remote.MovieEndpoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
//    single {
//        HttpLoggingInterceptor()
//            .setLevel(
//                if (BuildConfig.DEBUG) {
//                    HttpLoggingInterceptor.Level.HEADERS
//                } else {
//                    HttpLoggingInterceptor.Level.NONE
//                }
//            )
//    } withOptions {
//        createdAtStart()
//    }
//
//    single {
//        OkHttpClient.Builder()
//            .addInterceptor(get<HttpLoggingInterceptor>())
//            .addInterceptor { chain ->
//                chain.proceed(
//                    chain.request().newBuilder()
//                        .url(chain.request().url)
//                        .addHeader(
//                            name = "Authorization",
//                            value = "Bearer ${System.getProperties().getProperty("apiKey", "")}"
//                        )
//                        .build()
//                )
//            }
//            .build()
//    } withOptions {
//        createdAtStart()
//    }
//
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                    .addInterceptor { chain ->
                        val token = androidContext().getString(R.string.api_key)
                        val request = chain.request()
                            .newBuilder()
                            .addHeader(
                                name = "Authorization",
                                value = "Bearer $token"
                            )
                            .build()
                        chain.proceed(request)
                    }
                    .build()
            )
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
