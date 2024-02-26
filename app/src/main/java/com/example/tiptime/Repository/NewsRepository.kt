package com.example.tiptime.Repository

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.tiptime.API.NewsApi
import com.example.tiptime.AppController
import com.example.tiptime.DB.Room.NewsDao
import com.example.tiptime.Model.Article
import com.example.tiptime.Model.NewsResponse
import com.example.tiptime.Utils.Utils
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.withContext as coroutinesWithContext


class NewsRepository @Inject constructor(
    private val newsDao: NewsDao,
    @AppController.ApplicationContext private val context: Context,
    private val newsApi: NewsApi
) : Application() {
    init {
        initialize()
    }
    @OptIn(DelicateCoroutinesApi::class)
    private fun initialize() {
        GlobalScope.launch {
            try {
                val response = getNews("us", 1)

                if (response.isSuccessful) {
                    response.body()?.let { NewsResponse ->
                       upsert(article = null)

                    }
                }
            } catch (e: Exception) {
                Log.e("NEWS API", "Api Call")
            }
        }
    }
    private suspend fun upsert(article: Article?) {
        coroutinesWithContext(Dispatchers.IO) {
            newsDao.upsert(article)
        }
    }

    fun getSavedNews(): LiveData<List<Article>> {
        return newsDao.getAllNews()
    }
    suspend fun getNews(countryCode: String, pageNo: Int,
                            ): Response<NewsResponse> {
            try {
                if (!Utils.isNetworkAvailable(AppController.app)) {
                    throw IOException("Network connection error, Check and try again")
                }

                return coroutinesWithContext(Dispatchers.IO) {
                    try {
                        newsApi.getNews(countryCode, pageNo)
                    } catch (e: IOException) {
                        Log.e("NEWS API", "IOException: ${e.message}")
                        Response.error(500, ResponseBody.create(null, ""))
                    }
                }.also { response ->
                    if (response.isSuccessful) {
                        response.body()?.let {
                            newsDao.upsert(article = null)
                        }
                    }
                }
            } catch (e: IOException) {
                Log.d("NEWS API", "ApiCall")
                return Response.error(500, ResponseBody.create(null, ""))
            }
        }


}



