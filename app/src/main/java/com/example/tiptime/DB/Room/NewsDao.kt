package com.example.tiptime.DB.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tiptime.Model.Article
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(article: Article?) : Long

    @Query("SELECT * FROM 'Articles'")
    fun getAllNews() :LiveData<List<Article>>

    @Delete()
    fun deleteAllNews(article: Article)

}