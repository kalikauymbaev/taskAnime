package com.example.animequotesproject.di

import com.example.domain.usecase.AllAvailableAnimeUseCase
import com.example.domain.usecase.QuoteByAnimeTitleUseCase
import com.example.domain.usecase.RandomQuotesUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<AllAvailableAnimeUseCase> {
        AllAvailableAnimeUseCase(animeQuoteRepository = get())
    }

    factory<QuoteByAnimeTitleUseCase> {
        QuoteByAnimeTitleUseCase(animeQuoteRepository = get())
    }

    factory<RandomQuotesUseCase> {
        RandomQuotesUseCase(animeQuoteRepository = get())
    }
}