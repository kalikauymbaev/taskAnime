package com.example.domain.usecase

import com.example.domain.models.AnimeQuote
import com.example.domain.repository.AnimeQuoteRepository
import com.example.domain.usecase.base.UseCase
import kotlinx.coroutines.Dispatchers

class AllAvailableAnimeUseCase(private val animeQuoteRepository: AnimeQuoteRepository) {

    suspend fun execute(): List<String> {
        val result = animeQuoteRepository.getAllAvailableAnime()

        return result
    }

    //    override suspend fun execute(params: Any?): List<AnimeQuote>? {
//        withContext(defaultDispatcher) {
//            checkNotNull(params)
//            animeQuoteRepository.getAllAvailableAnime()
//        }
//    }
//    override suspend fun execute(params: List<AnimeQuote>?): List<String> {
//            return animeQuoteRepository.getAllAvailableAnime()
//    }
}