package com.example.tiptime.Repository

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.util.newStringBuilder
import com.example.tiptime.API.NewsApi
import com.example.tiptime.DB.RetrofitInstance
import com.example.tiptime.DB.Room.NewsDao
import com.example.tiptime.Model.Article
import com.example.tiptime.Model.NewsResponse
import com.example.tiptime.Utils.Resource
import com.example.tiptime.Utils.Utils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observable.merge
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.internal.operators.observable.ObservableObserveOn
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.newSingleThreadContext
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val newsDao: NewsDao,
    private val newsApi: NewsApi,
    private val context: Context
    ) {

    @SuppressLint("CheckResult")
    fun getLiveNews() : Observable<List<Article>> {
        Log.d("Repository", "Fetching News")
        val apiNews = getApiNews()

            .flatMap { newsResponse ->
                val articles = newsResponse.articles
                Observable.just(articles)
            }
            .onErrorResumeNext { throwable: Throwable ->
                Log.e("Repository", "Api call failed: ${throwable.message}}")

                Observable.empty()
            }
            val roomNews = getRoomNews()
        return merge(apiNews, roomNews)


    }

     fun getApiNews(): Observable<NewsResponse> {
        Log.d("Repository", "Api Call")
        return newsApi.getNews()
            .doOnError { error ->
                Log.e("Repository", "News Error")
            }
            .doOnNext { newsResponse ->
                Log.d("Repository", "Api Response: $newsResponse")
                if (newsResponse.articles.isNotEmpty()) {
                    Log.e("News Repo ", "We got news on our API")
                    storeNewsInDb(newsResponse.articles)
                } else {
                    Log.e("News Repo", "No articles from API")
                }
            }
            .subscribeOn(Schedulers.io())
    }

    fun getRoomNews() : Observable<List<Article>> {
        Log.d("Repository", "Room  Call")
       return try {
//           if (!Utils.isNetworkAvailable(context)) {
               newsDao.getAllNews()
                   .filter() { it.isNotEmpty() }
                   .toObservable()
                   .doOnNext {
                       Log.e("News Repo ${it.size}", "We got news in room")
                   }
//           } else {
//               Observable.switchOnNext() {
//                   getApiNews()
//               }

//           }
       }catch (e: Exception) {
           Log.e("News Repo", "Network Exceptions $newsDao")
           Observable.error(e)
       }
    }

    @SuppressLint("CheckResult")
    fun storeNewsInDb(article: List<Article>) {
        Log.d("Repository", "Storing news in DB: ${article.size} articles")
        Completable.fromCallable { newsDao.upsert(article) }
            .subscribeOn(Schedulers.io())

            .subscribe(
                { Log.i("News Repo", "data successful")
            },
        { error -> Log.e("News Repo", "Error storing data", error) }
    )
    }

}





