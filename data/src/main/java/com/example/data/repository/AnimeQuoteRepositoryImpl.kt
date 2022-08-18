package com.example.data.repository

import com.example.data.retrofit.RetrofitService
import com.example.domain.models.AnimeQuote
import com.example.domain.repository.AnimeQuoteRepository

class AnimeQuoteRepositoryImpl(private val retrofitService: RetrofitService): AnimeQuoteRepository {

    override suspend fun getRandomQuotes(): List<AnimeQuote> {
        return retrofitService.getRandomQuotes()
    }

    override suspend fun getAllAvailableAnime(): List<String> {
        return retrofitService.getAllAvailableAnime()
    }

    override suspend fun getAnimeByTitle(title: String): List<AnimeQuote> {
        return retrofitService.getQuoteByAnimeTitle(title)
    }
}