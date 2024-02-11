package com.example.tiptime.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiptime.API.NewsApi
import com.example.tiptime.Model.Article
import com.example.tiptime.Model.NewsResponse
import com.example.tiptime.Repository.NewsRepository
import com.example.tiptime.Utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class NewsViewModel (private val repository: NewsRepository) : ViewModel() {


    val news: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val savedArticles: LiveData<List<Article>> = repository.getSavedNews()
    private var topHeadlinesPage = 1


    init {
        Log.d("NewsViewModel", "ViewModel Initialized")
//        getNews("us")
    }

    fun getNews(countryCode: String) = viewModelScope.launch {
        try {
            news.postValue(Resource.Loading())

            val response = withContext(Dispatchers.IO) {
                repository.getNews(countryCode, topHeadlinesPage)
            }
            withContext(Dispatchers.Main) {
                news.postValue(handleBreakingResponse(response))
            }
//            news.postValue(handleBreakingResponse(response))

        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                news.postValue(Resource.Error(e.message ?: "Unknown Error"))
            }

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

    fun saveArticle(article: Article) = viewModelScope.launch {
        repository.upsert(article)
    }
//    fun getSavedNews() = repository.getSavedNews()

}

//    fun fetchNewsList(context: Context): Disposable {
//        return Observable.fromCallable {
//
//            fetchNewsOffline()
//
//            if (!Utils.isNetworkAvailable(context)) {
//                throw NetworkUnavailableException("Internet not Connected")
//            }
//            val response = newsApi.getPaymentTypes("in", API_KEY, 50)
//
//            if (response.isSuccessful) {
//                insertNews(response.body()?.articles)
//            } else {
//                throw NetworkErrorException("Error loading data, Try again later")
//            }
//
//            response.body()
//
//        }
//            .subscribeOn(Schedulers.io())
//            .observeOn(Schedulers.io())
//            .subscribe({ result ->
//                newsListLiveData.postValue(result)
//            },
//                {
//
//                })
//
//    }
//
//    class NetworkUnavailableException(message: String) : Exception(message)
//
//    @SuppressLint("CheckResult")
//    private fun fetchNewsOffline() {
//        Observable.fromCallable {
//            newsDatabase.getNewsDao()
//        }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ result ->
//
//                result.subscribe({ newsList ->
//                    val newsArrayList = ArrayList(newsList)
//                    val vewsDatabase = NewsApi("ok", "", newsArrayList)
//                    newsListLiveData.postValue(dbData)
//                }, {
//
//                    Log.e("news", "no news in flows")
//
//                })
//            }, {
//                Log.e("news", "no news in room")
//            })
//    }
//
//        @SuppressLint("CheckResult")
//        fun insertNews(newsList: ArrayList<NewsData>?) {
//            Observable.fromCallable {
//                var needsUpdate = false
//                newsList?.forEach { item ->
//                    val inserted = newsDao.insertNews(item)
//                    if (inserted == -1L) {
//                        val updated = newsDao.insertNews(item)
//                        if (updated > 0) {
//                            needsUpdate = true
//
//                        }
//
//                    } else {
//                        needsUpdate = true
//                    }
//                }
//                needsUpdate
//            }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe { needsUpdate ->
//                    if (needsUpdate) {
//                        fetchNewsOffline()
//                    }
//                }
//        }
//
//}















