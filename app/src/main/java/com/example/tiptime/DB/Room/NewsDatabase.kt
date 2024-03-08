package com.example.tiptime.DB.Room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.impl.Migration_1_2
import com.example.tiptime.Model.Article


@TypeConverters(DataConverter::class)
@Database(entities = [Article::class], version = 7, exportSchema = false)
abstract class NewsDatabase: RoomDatabase(){

    abstract fun getNewsDao(): NewsDao


    companion object {
        @Volatile
        private var instance: NewsDatabase? = null
        private val LOCK = Any()

//         val migration_1_2: Migration = object : Migration(1, 3) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("Update Article SET newColumn = oldColumn")
//                database.execSQL("ALTER TABLE Article DROP COLUMN oldColumn")
//
//            }
//
//        }
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it}
        }
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "news_db.db")
//                .addMigrations(migration_1_2)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }
}