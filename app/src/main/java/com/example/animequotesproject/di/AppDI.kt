package com.example.animequotesproject.di

import com.example.animequotesproject.presentation.mvvm.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainViewModel> {
        MainViewModel(
            allAvailableAnimeUseCase = get(),
            randomQuotesUseCase = get(),
            quoteByAnimeTitleUseCase = get()
        )
    }
}