package com.example.animequotesproject.presentation.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.AnimeQuote
import com.example.data.retrofit.RetrofitService
import com.example.data.repository.AnimeQuoteRepositoryImpl
import com.example.domain.usecase.AllAvailableAnimeUseCase
import com.example.domain.usecase.QuoteByAnimeTitleUseCase
import com.example.domain.usecase.RandomQuotesUseCase
//import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import org.koin.core.qualifier.Qualifier

class MainViewModel(
    private val quoteByAnimeTitleUseCase: QuoteByAnimeTitleUseCase,
    private val randomQuotesUseCase: RandomQuotesUseCase,
    private val allAvailableAnimeUseCase: AllAvailableAnimeUseCase
) : ViewModel() {

    private val retrofitService = RetrofitService.getInstance()
//    private val animeQuoteRepositoryImpl by lazy { AnimeQuoteRepositoryImpl(retrofitService = retrofitService) }
//    quoteByAnimeTitleUseCase =  QuoteByAnimeTitleUseCase(animeQuoteRepositoryImpl)
//    randomQuotesUseCase = RandomQuotesUseCase(animeQuoteRepositoryImpl)
//    allAvailableAnimeUseCase = AllAvailableAnimeUseCase(animeQuoteRepositoryImpl)

    val errorMessage = MutableLiveData<String>()
    val randomQuotes = MutableLiveData<List<AnimeQuote>>()
    val availableAnime = MutableLiveData<List<String>>()
    val animeTitle = MutableLiveData<List<AnimeQuote>>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
        Log.i("TAG123", throwable.localizedMessage)
    }
    val loading = MutableLiveData<Boolean>()

    fun getRandomQuotes() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            randomQuotes.postValue(randomQuotesUseCase.execute())
            loading.postValue(false)
        }
//        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
//            val response = randomQuotesUseCase.execute()
//            withContext(Dispatchers.Main) {
//
//                randomQuotes.postValue(response.body())
//                loading.value = false
//
//                onError("Error : ${response.message()} ")
//
//            }
//        }

    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun getAllAvailableAnime() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            availableAnime.postValue(allAvailableAnimeUseCase.execute())
            loading.postValue(false)
        }
//        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
//            val response = allAvailableAnimeUseCase.execute()
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//                    availableAnime.postValue(response.body())
//                    loading.value = false
//                } else {
//                    onError("Error : ${response.message()} ")
//                }
//            }
//        }
    }
//
    fun getAnimeByTitle(title: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            animeTitle.postValue(quoteByAnimeTitleUseCase.execute(title))
            loading.postValue(false)
//            val response = quoteByAnimeTitleUseCase.execute(title)
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//                    animeTitle.postValue(response.body())
//                    loading.value = false
//                } else {
//                    onError("Error : ${response.message()} ")
//                }
//            }
        }
    }

}