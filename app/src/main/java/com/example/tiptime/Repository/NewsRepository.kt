package com.example.tiptime.Repository

import androidx.lifecycle.LiveData
import androidx.room.Database
import com.example.tiptime.API.NewsApi
import com.example.tiptime.DB.RetrofitInstance
import com.example.tiptime.DB.Room.NewsDao
import com.example.tiptime.DB.Room.NewsDatabase
import com.example.tiptime.Model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class NewsRepository(
//    val db: Database
    val newsDao: NewsDao

) {


   suspend fun getNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getNews(countryCode, pageNumber)

//    Updating database
//    suspend fun upsert(article: Article) = db.getNewsDao().upsert(article)
    suspend fun upsert(article: Article) {
        withContext(Dispatchers.IO) {
            newsDao.upsert(article)
        }
    }

//    getting saved news from my database
    fun getSavedNews(): LiveData<List<Article>> {
        return newsDao.getAllNews()
    }

//    fun getSavedNews() = db.getNewsDao().getAllNews()

}



//    private val compositeDisposable = CompositeDisposable()


//    private val _newsListLiveData: MutableLiveData<<ArrayList<NewsData>>?> =
//        repository.newsListLiveData
//
//    val newsListLiveData: MutableLiveData<BaseModel<ArrayList<NewsData>>?>
//        get() = _newsListLiveData
//
//    fun fetchNewsList(context: Context) {
//        compositeDisposable.add(repository.fetchNewsList(context))
//
//    }
//    override fun onCleared() {
//        super.onCleared()
//        compositeDisposable.clear()
//    }




