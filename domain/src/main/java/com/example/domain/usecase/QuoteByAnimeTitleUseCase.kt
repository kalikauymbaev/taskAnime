package com.example.domain.usecase

import com.example.domain.models.AnimeQuote
import com.example.domain.repository.AnimeQuoteRepository
import com.example.domain.usecase.base.UseCase
import kotlinx.coroutines.Dispatchers

class QuoteByAnimeTitleUseCase(private val animeQuoteRepository: AnimeQuoteRepository){

    suspend fun execute(title: String): List<AnimeQuote> {
        val result = animeQuoteRepository.getAnimeByTitle(title)

        return result
    }

//    override suspend fun execute(title: String): Any? {
//        return animeQuoteRepository.getAnimeByTitle(title)
//    }

}