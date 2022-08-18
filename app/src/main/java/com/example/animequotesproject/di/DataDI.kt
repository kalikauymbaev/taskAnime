package com.example.animequotesproject.di

import com.example.data.repository.AnimeQuoteRepositoryImpl
import com.example.data.retrofit.RetrofitService
import com.example.domain.repository.AnimeQuoteRepository
import org.koin.dsl.module

val dataModule = module {

    single {
        RetrofitService.getInstance()
    }

    single<AnimeQuoteRepository>{
        AnimeQuoteRepositoryImpl(retrofitService = get())
    }

}