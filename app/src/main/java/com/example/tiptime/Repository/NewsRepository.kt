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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


class NewsRepository @Inject constructor(
    val newsDao: NewsDao,
    @AppController.ApplicationContext private val application: Application,
    private val newsApi: NewsApi
) : Application() {
    init {
        CoroutineScope(Dispatchers.IO).launch{
            val response = getNews("us", 1 )

            Log.i("NewsRepo_main", "${response.body()}")

        }
    }

    suspend fun getNews(countryCode: String, pageNo: Int): Response<NewsResponse> {
        try {
            if (!Utils.isNetworkAvailable(application)) {
                Log.d("NEWS API", "News Api Network check successful")
                throw IOException("Network connection error, Check and try again")
//                return Response.error(500, ResponseBody.create(null, ""))
            }
            val response = coroutineScope {
                try {
                    newsApi.getNews(countryCode, pageNo)
                } catch (e: IOException) {
                    Log.e("NEWS Repo", "IOException: ${e.message}")
                    Response.error(500, ResponseBody.create(null, ""))
                }
            }
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let { newsResponse ->
                        Log.d("News repo", "Success News Response")


                }

            }
            return Response.error(500, ResponseBody.create(null, ""))

        } catch (e: IOException) {
            Log.d("NEWS API", "ApiCall")
            return Response.error(500, ResponseBody.create(null, ""))
        }
    }

    suspend fun addArticles(article: Article) {
        coroutineScope {
            newsDao.addArticles(article)
        }
    }

    fun getSavedNews(): LiveData<List<Article>> {
        Log.d("SAVED NEWS", "Failed Getting Saved News")
        return newsDao.getAllNews()
    }

}



