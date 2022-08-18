package com.example.data.dto

import com.example.domain.models.AnimeQuote

class FullInfo{

    var anime: String? = null
    var character: String? = null
    var quote: String? = null

fun toEntity(): AnimeQuote {
        val dto = this
        return AnimeQuote().apply {
            anime = dto.anime
            character = dto.character
            quote = dto.quote
        }
    }
}
