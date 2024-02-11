package com.example.tiptime.API

import com.example.tiptime.Model.NewsResponse
import com.example.tiptime.Utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines")
   suspend fun getNews(
        @Query("country")
        countryCode: String = "us",
        @Query("pageNumber")
        pageNumber: Int = 1,
        @Query("apikey")
    apiKey: String = API_KEY


    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")searchQuery: String,
        @Query("apikey")
        apiKey: String = API_KEY,
        @Query("pageNumber")
        pageNumber: Int = 1
    )
}