package com.example.tiptime.Model

data class NewsResponse(
    var status: String,
    val totalResults: Int,
    var articles: List<Article>

)