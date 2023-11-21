package com.example.tiptime.network.Models


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceHolder {
    private fun loggingInterceptor():HttpLoggingInterceptor
    {
        val logging = HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }
    private fun okHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor())
            .build()
    }


    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory((GsonConverterFactory.create()))
        .client(okHttp())
        .build()




    fun<T> builderService(service: Class<T>):T {
        return retrofit.create(service)
    }
}