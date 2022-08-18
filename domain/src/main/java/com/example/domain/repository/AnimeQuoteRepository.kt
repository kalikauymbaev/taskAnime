package com.example.domain.repository

import com.example.domain.models.AnimeQuote

interface AnimeQuoteRepository {

    suspend fun getRandomQuotes(): List<AnimeQuote>

    suspend fun getAllAvailableAnime(): List<String>

    suspend fun getAnimeByTitle(title: String): List<AnimeQuote>

}