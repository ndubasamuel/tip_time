package com.example.tiptime.API

import com.example.tiptime.Model.Article
import com.example.tiptime.Model.NewsResponse
import com.example.tiptime.Utils.Constants.Companion.API_KEY
import com.example.tiptime.Utils.Resource
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines")
   fun getNews(
        @Query("country")
        country: String = "us",
        @Query("category")
        category: String = "business",
        @Query("apikey")
        apiKey: String = API_KEY


    ): Observable<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")searchQuery: String,
        @Query("apikey")
        apiKey: String = API_KEY,
        @Query("pageNumber")
        pageNumber: Int
    )
}