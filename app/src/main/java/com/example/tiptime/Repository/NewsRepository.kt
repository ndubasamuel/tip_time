package com.example.tiptime.Repository

import android.content.Context
import android.util.Log
import com.example.tiptime.API.NewsApi
import com.example.tiptime.DB.Room.NewsDao
import com.example.tiptime.Model.Article
import com.example.tiptime.Utils.Utils
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val newsDao: NewsDao,
    private val newsApi: NewsApi,
    private val context: Context
    ) {

    suspend fun handleNews(): List<Article>?{
        Log.d("Repository", "News Call")
       return try {
           Log.d("Repository", "Network initiation")
            if (!Utils.isNetworkAvailable(context)) {
                Log.d("Repository", "Network check")

                val apiCall = newsApi.getNews()

                if (apiCall.isSuccessful) {

                    Log.d("Repo", "News Check Done $apiCall")

                    val articles = apiCall.body()?.articles
                    articles?.let {
                    Log.d("Repo", "Articles upsert $it")
                        newsDao.upsert(it) }
                }
                (apiCall.body()?.articles)
            } else {

                newsDao.getAllNews()

            }
        } catch (e: Exception) {
            e.printStackTrace()
           (null)
        }


    }

//    suspend fun getLiveNews():  {
//        Log.d("Repo", "Handle news response ${handleNews()}")
//
//        return handleNews()
//
//
//    }
}





