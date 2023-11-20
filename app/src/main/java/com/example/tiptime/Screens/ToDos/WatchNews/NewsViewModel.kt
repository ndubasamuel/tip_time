package com.example.tiptime.Screens.ToDos.WatchNews

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tiptime.network.Models.ApiResponse
import com.example.tiptime.network.Models.News
import com.example.tiptime.network.Models.ServiceHolder
import com.example.tiptime.network.Models.ServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel: ViewModel() {
    private val _news: MutableLiveData<List<News>?> = MutableLiveData()
    val news: MutableLiveData<List<News>?> = _news

    fun fetchNews() {
        val retrofit = ServiceHolder.builderService(ServiceInterface::class.java)

        retrofit.getAllNews(
            "tesla",
            "2023-10-20",
            "publishedAt",
            "b3075ce86ddd47b2866543f66c7bc382")
            .enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.let {
                        val newsList = it.news
                        _news.postValue(newsList)
                    }
                } else {
                    // Handle unsuccessful response
                    _news.postValue(emptyList())
                    Log.e("API Error", "Response not successful: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Handle failure cases here
                // You can post an empty list or handle the failure differently
                _news.postValue(emptyList())
                Log.e("API Error", "API call failed: ${t.message}")
            }
        })
    }

}




