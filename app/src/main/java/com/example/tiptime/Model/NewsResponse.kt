package com.example.tiptime.Model

import androidx.room.Entity


data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)