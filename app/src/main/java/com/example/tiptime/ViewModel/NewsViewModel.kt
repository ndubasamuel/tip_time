package com.example.tiptime.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tiptime.Model.Article
import com.example.tiptime.Repository.NewsRepository
import com.example.tiptime.Utils.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private var _liveNews = MutableLiveData<Resource<List<Article>>>(Resource.Loading())
    val liveNews: LiveData<Resource<List<Article>>> = _liveNews
    private var job: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
    }


    fun getLiveNews() {
        job = CoroutineScope(Dispatchers.IO + this.exceptionHandler).launch {
            val response = repository.handleNews()
            Log.d("ViewModel", "News Trigger ${response?.size}")
            withContext(Dispatchers.Main) {
                response?.let { articles ->
                    _liveNews.value = Resource.Success(articles)
                } ?: run {
                    _liveNews.value = Resource.Error("Failed to fetch news data")
                }

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}



