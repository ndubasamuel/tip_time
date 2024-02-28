package com.example.tiptime.Model

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    var articles: List<Article>

)