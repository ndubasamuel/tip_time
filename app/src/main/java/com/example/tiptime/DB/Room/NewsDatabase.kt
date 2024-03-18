package com.example.tiptime.DB.Room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tiptime.Model.Article


@TypeConverters(DataConverter::class)
@Database(entities = [Article::class], version = 2, exportSchema = false)
abstract class NewsDatabase: RoomDatabase(){

    abstract fun getNewsDao(): NewsDao

    companion object {
        @Volatile
        private var instance: NewsDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it}
        }
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "news_db.db"
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }
}