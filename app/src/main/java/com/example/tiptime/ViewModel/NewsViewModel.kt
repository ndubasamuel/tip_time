package com.example.tiptime.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiptime.Model.NewsResponse
import com.example.tiptime.Repository.NewsRepository
import com.example.tiptime.Utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class NewsViewModel (private val repository: NewsRepository) : ViewModel() {


    val news: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    private var topHeadlinesPage = 1

    init {
        Log.d("NewsViewModel", "ViewModel Initialized")
        getNews("us")
    }

    private fun getNews(countryCode: String) = viewModelScope.launch {
        try {
            news.postValue(Resource.Loading())

            val response = withContext(Dispatchers.IO) {
                repository.getNews(countryCode, topHeadlinesPage)
            }
            news.postValue(handleBreakingResponse(response))

        } catch (e: Exception) {
            news.postValue(Resource.Error(e.message ?: "Unknown Error"))

    }



//        val response = repository.getNews(countryCode, topHeadlinesPage)
//        news.postValue(handleBreakingResponse(response))

    }

    private fun handleBreakingResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)

            }
        }
        return Resource.Error(response.message())
    }

}

//    private val compositeDisposable = CompositeDisposable()


//    private val _newsListLiveData: MutableLiveData<<ArrayList<NewsData>>?> =
//        repository.newsListLiveData
//
//    val newsListLiveData: MutableLiveData<BaseModel<ArrayList<NewsData>>?>
//        get() = _newsListLiveData
//
//    fun fetchNewsList(context: Context) {
//        compositeDisposable.add(repository.fetchNewsList(context))
//
//    }
//    override fun onCleared() {
//        super.onCleared()
//        compositeDisposable.clear()
//    }
















