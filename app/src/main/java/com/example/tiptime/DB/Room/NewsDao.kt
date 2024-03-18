package com.example.tiptime.DB.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tiptime.Model.Article

//typealias Article = List<Article>
@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: List<Article>?)


    @Query("SELECT * FROM 'Articles'")
    fun getAllNews() : List<Article>

    @Delete()
    fun deleteAllNews(article: Article?)

}