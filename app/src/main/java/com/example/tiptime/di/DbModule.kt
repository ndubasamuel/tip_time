package com.example.tiptime.di

import android.app.Application
import androidx.room.Room
import com.example.tiptime.DB.Room.NewsDao
import com.example.tiptime.DB.Room.NewsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class DbModule (mApplication: Application){
    private val newsDatabase: NewsDatabase = Room.databaseBuilder(mApplication, NewsDatabase::class.java, "news_db.db")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
    @Singleton
    @Provides
    internal fun providesRoomDatabase(): NewsDatabase {
        return newsDatabase
    }

    @Singleton
    @Provides
    internal fun providesNewsDao(newsDatabase: NewsDatabase): NewsDao {
        return newsDatabase.getNewsDao()
    }

}