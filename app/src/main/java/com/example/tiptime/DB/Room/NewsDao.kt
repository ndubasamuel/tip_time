package com.example.tiptime.DB.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tiptime.Model.Article
import com.example.tiptime.Model.NewsResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(article: List<Article>?)
//    fun upsert(newsResponse: NewsResponse)

    @Query("SELECT * FROM 'Articles'")
    fun getAllNews() : Single<List<Article>>

    @Delete()
    fun deleteAllNews(article: Article)

}