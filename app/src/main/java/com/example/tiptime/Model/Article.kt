package com.example.tiptime.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.work.Data
import com.example.tiptime.DB.Room.DataConverter
import java.io.Serializable

@Entity(tableName ="Articles")
data class Article (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    val source: Source,
    val title: String = "",
    val url: String = "",
    val urlToImage: String? = ""


) : Serializable