package com.example.tiptime.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tiptime.API.NewsApi
import com.example.tiptime.DB.Room.NewsDatabase
import com.example.tiptime.Repository.NewsRepository
import dagger.Module
import javax.inject.Inject

@Module
class NewsViewModelFactory @Inject constructor(val repository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
//            val repository = NewsRepository(application)
            @Suppress("UNCHECKED_CAST")
            return NewsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}