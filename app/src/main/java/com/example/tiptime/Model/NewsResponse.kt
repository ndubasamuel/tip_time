package com.example.tiptime.Model

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)