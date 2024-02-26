package com.example.tiptime.di

import android.app.Application
import com.example.tiptime.API.NewsApi
import com.example.tiptime.AppController
import com.example.tiptime.DB.RetrofitInstance
import com.example.tiptime.MainActivity
import com.example.tiptime.Repository.NewsRepository
import com.example.tiptime.Screens.ToDos.WatchNews.NewsFragment
import com.example.tiptime.ViewModel.NewsViewModel
import com.example.tiptime.ViewModel.NewsViewModelFactory
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component (modules = [RetrofitInstance::class, AppModule::class, DbModule::class])
interface ApiComponent  {
    fun inject(activity: MainActivity)
    fun inject(application: AppController)
    fun inject(newsFragment: NewsFragment)
    fun inject(newsViewModelFactory: NewsViewModelFactory)


    fun provideNewsApi(): NewsApi

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun retrofitInstance(retrofitInstance: RetrofitInstance): Builder
        fun dbModule(dbModule: DbModule) : Builder

        fun build() : ApiComponent
    }
}