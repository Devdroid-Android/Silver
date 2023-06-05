package com.satyamthakur.silver.sl

import com.satyamthakur.silver.ui.screen.dashboard.viewmodel.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DashboardViewModel(get()) }
}