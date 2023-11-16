package com.example.tiptime.network.Models

interface OnFetchDataListener<NewsApiResponse>{

    fun onFetchData(list: List<NewsHeadlines>, message: String)
    fun onError(message: String)


}