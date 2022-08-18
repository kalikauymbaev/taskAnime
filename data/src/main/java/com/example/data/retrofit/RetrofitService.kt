package com.example.data.retrofit

import com.example.domain.models.AnimeQuote
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("quotes")
    suspend fun getRandomQuotes() : List<AnimeQuote>

    @GET("available/anime")
    suspend fun getAllAvailableAnime() : List<String>

    @GET("quotes/anime")
    suspend fun getQuoteByAnimeTitle(
        @Query("title") title: String
    ) : List<AnimeQuote>


    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://animechan.vercel.app/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}