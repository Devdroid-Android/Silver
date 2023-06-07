package com.satyamthakur.silver.app

import android.app.Application
import com.satyamthakur.silver.sl.networkModule
import com.satyamthakur.silver.sl.repositoryModule
import com.satyamthakur.silver.sl.viewModelModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class SilverApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger(
                if (BuildConfig.DEBUG) {
                    Level.INFO
                } else {
                    Level.NONE
                }
            )
            androidContext(this@SilverApplication)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}
