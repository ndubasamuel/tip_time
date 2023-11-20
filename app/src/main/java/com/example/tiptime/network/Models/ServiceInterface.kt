package com.example.tiptime.network.Models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ServiceInterface {
    @GET("everything")
    fun getAllNews(
//    @Query("country") country: String,
//    @Query("category") category: String,
    @Query("q") query: String,
    @Query("from")from: String,
    @Query("sortBy")sortBy: String,
    @Query("apikey") apiKey: String
    ): Call<ApiResponse>
}