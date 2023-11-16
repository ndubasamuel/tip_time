package com.example.tiptime.network.Models

data class NewsApiResponse (
    var status: String,
    var totalResults: Int,
    var articles: List<NewsHeadlines>)