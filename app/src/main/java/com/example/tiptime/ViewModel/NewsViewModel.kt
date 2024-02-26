package com.example.tiptime.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tiptime.API.NewsApi
import com.example.tiptime.Model.Article
import com.example.tiptime.Model.NewsResponse
import com.example.tiptime.Repository.NewsRepository
import com.example.tiptime.Utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val repository: NewsRepository,
                                        newsApi: NewsApi,
    application: Application) : AndroidViewModel(application) {


//        val getNews: MutableLiveData<Response<NewsResponse>> = MutableLiveData()
    val savedNews: LiveData<List<Article>> = repository.getSavedNews()
//    private var pageNo = 1

    init {
        Log.d("NewsViewModel", "ViewModel Initialized")
        getSavedNews()



    }
    suspend fun getNews() = repository.getNews(countryCode =  "", pageNo = 1)



    private fun getSavedNews() = viewModelScope.launch {
        try {
            Log.d("FETCHING NEWS", "Starting")

            getNews()
            val savedNews = repository.getSavedNews()

            savedNews.observeForever { articles ->
                Log.d("SAVED NEWS", "Size: ${articles.size}")

            }
        } catch (e: Exception) {
            Log.d("NEWS LOAD", "Failed to load Saved News")
        }
        return@launch
    }

}


//    }

//    private fun handleBreakingResponse(response: Response<NewsResponse>) {
//         if (response.isSuccessful) {
//            response.body()?.let { resultResponse ->
//                Resource.Success(resultResponse)
//            } ?: run {
//                Resource.Error("No Response")
//            }
//        } else {
//
//            handleHttpError(response.code())
//        }
//    }
// Handling Network failure
//    private fun handleNoNetwork() {
//        news.postValue(Resource.Error("No internet Connection, Check and try again"))
//        loadSavedArticles()
//    }

//    private fun handleNetworkError() {
//        news.postValue(Resource.Error("Cannot refresh, Network Error"))
//        loadSavedArticles()
//    }
//    Handling Http Error
//    private fun handleHttpError(errorCode: Int): Resource<NewsResponse> {
//        val errorMessage = when (errorCode) {
//            400 -> "Check your connection, Bad request"
//            500 -> "Server Error"
//            503 -> "Service Unavailable"
//            401 -> "Unauthorized access"
//            else -> "ERROR: $errorCode"
//        }
//
//        news.postValue(Resource.Error(errorMessage))
//        return Resource.Error(errorMessage)
//
//    }

//    private fun loadSavedArticles() {
//        viewModelScope.launch {
//            savedArticles.value?.let { savedArticlesList ->
//                if (savedArticlesList.isNotEmpty()) {
//                    news.postValue(Resource.Success(NewsResponse("ok", 1, savedArticlesList)))
//                } else {
//                    news.postValue(Resource.Error("No Saved News"))
//                }
//            } ?: run {
//                news.postValue(Resource.Error("Error Loading Saved News"))
//            }
//        }
//
//    }
//    fun saveArticle(article: Article) = viewModelScope.launch {
//        repository.upsert(article)
//    }
//
//}








