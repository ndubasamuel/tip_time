package com.example.tiptime.API

import androidx.lifecycle.LiveData
import com.example.tiptime.DB.RetrofitInstance
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
        country: String = "us",
        @Query("category")
        category: String = "entertainment",
        @Query("pageSize")
        pageNumber: Int = 30,
        @Query("apiKey")
        apiKey: String = RetrofitInstance().apiKey



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