package com.example.tiptime.API

import androidx.lifecycle.LiveData
import com.example.tiptime.Model.NewsResponse
import com.example.tiptime.Utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines")
   suspend fun getNews(
        @Query("country")
        searchQuery: String = "us",
        @Query("pageNumber")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = ""



    ): Response<NewsResponse>

    @GET("v2/top-headlines")
    suspend fun searchForNews(
        @Query("q")searchQuery: String,
        @Query("apikey")
        apiKey: String,
        @Query("pageNumber")
        pageNumber: Int = 1
    )

}