package com.example.domain.usecase

import com.example.domain.models.AnimeQuote
import com.example.domain.repository.AnimeQuoteRepository

class RandomQuotesUseCase(private val animeQuoteRepository: AnimeQuoteRepository){

    suspend fun execute(): List<AnimeQuote> {
        return animeQuoteRepository.getRandomQuotes()
    }

//    override suspend fun execute(params: List<AnimeQuote>?): Any {
//        return animeQuoteRepository.getRandomQuotes()
//    }

}