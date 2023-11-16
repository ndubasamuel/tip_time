package com.example.tiptime.network.Models

import android.content.Context
import android.util.Log
import com.example.tiptime.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class RequestManager(private var context: Context) {

    private val retrofit: Retrofit = retrofit2.Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getNewsHeadlines(listener: OnFetchDataListener<NewsApiResponse>, category: String, query: String?) {
        val callNewsApi: CallNewsApi = retrofit.create(CallNewsApi::class.java)

        try {
            query?.let {
                callNewsApi.callHeadlines(
                    "us", category,
                    it, context.getString(R.string.Api_Key)
                )
            }?.enqueue(object : Callback<NewsApiResponse> {
                override fun onResponse(
                    call: Call<NewsApiResponse>,
                    response: Response<NewsApiResponse>
                ) {
                    if (response.isSuccessful) {
                        val newsList = response.body()?.articles ?: emptyList()
                        listener.onFetchData(newsList, "Success")
                    } else {
                        listener.onError("Error: ${response.code()}")
                        Log.e("Request manager", "error")
                    }
                }

                override fun onFailure(call: Call<NewsApiResponse>, t: Throwable) {
                    listener.onError("Error: ${t.message}")
                    Log.e("Request manager", "Request failed")
                }
            })

        } catch (e: Exception) {
            listener.onError("Error: ${e.message}")
            Log.e("Request manager", "Network error")
        }
    }
    interface CallNewsApi {
        @GET("top-headlines")
        fun callHeadlines(
            @Query("country") country: String,
            @Query("category") category: String,
            @Query("q") query: String,
            @Query("apikey") api_key: String
        ): Call<NewsApiResponse>
    }
}