package com.example.tiptime.ViewModel

import android.annotation.SuppressLint
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tiptime.Model.Article
import com.example.tiptime.Model.NewsResponse
import com.example.tiptime.Repository.NewsRepository
import com.example.tiptime.Utils.Resource
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.Error
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {


    private val _newsLiveData = MutableLiveData<Resource<List<Article>>>()
    val newsLiveData: LiveData<Resource<List<Article>>> get() = _newsLiveData


     private val apiDisposable: Disposable =
        repository.getLiveNews()
            .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {news ->
                    Log.d("ViewModel", "successful z $news")
                    _newsLiveData.value = Resource.Success(news)
                },

                {
                        error ->
                    Log.e("ViewModel", "Error z")
                    _newsLiveData.value = error.message?.let { Resource.Error(it) }

                }
            )

    override fun onCleared() {
        super.onCleared()
        apiDisposable.dispose()
    }

    @SuppressLint("CheckResult")
    fun fetchNews(){
        Log.d("ViewModel", "Fetching news")
    }






}

