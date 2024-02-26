package com.example.tiptime.di

import android.app.Application
import android.content.Context
import com.example.tiptime.AppController
import com.example.tiptime.DB.Room.NewsDao
import com.example.tiptime.DB.Room.NewsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application

    }

}