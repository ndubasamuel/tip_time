package com.example.tiptime.Repository

import com.example.tiptime.DB.RetrofitInstance
import com.example.tiptime.DB.Room.NewsDatabase


class NewsRepository(
     val db: NewsDatabase) {


   suspend fun getNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getNews(countryCode, pageNumber)


}


//    fun fetchNewsList(context: Context): Disposable {
//        return Observable.fromCallable {
//
//            fetchNewsOffline()
//
//            if (!Utils.isNetworkAvailable(context)) {
//                throw NetworkUnavailableException("Internet not Connected")
//            }
//            val response = newsApi.getPaymentTypes("in", API_KEY, 50)
//
//            if (response.isSuccessful) {
//                insertNews(response.body()?.articles)
//            } else {
//                throw NetworkErrorException("Error loading data, Try again later")
//            }
//
//            response.body()
//
//        }
//            .subscribeOn(Schedulers.io())
//            .observeOn(Schedulers.io())
//            .subscribe({ result ->
//                newsListLiveData.postValue(result)
//            },
//                {
//
//                })
//
//    }
//
//    class NetworkUnavailableException(message: String) : Exception(message)
//
//    @SuppressLint("CheckResult")
//    private fun fetchNewsOffline() {
//        Observable.fromCallable {
//            newsDatabase.getNewsDao()
//        }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ result ->
//
//                result.subscribe({ newsList ->
//                    val newsArrayList = ArrayList(newsList)
//                    val vewsDatabase = NewsApi("ok", "", newsArrayList)
//                    newsListLiveData.postValue(dbData)
//                }, {
//
//                    Log.e("news", "no news in flows")
//
//                })
//            }, {
//                Log.e("news", "no news in room")
//            })
//    }
//
//        @SuppressLint("CheckResult")
//        fun insertNews(newsList: ArrayList<NewsData>?) {
//            Observable.fromCallable {
//                var needsUpdate = false
//                newsList?.forEach { item ->
//                    val inserted = newsDao.insertNews(item)
//                    if (inserted == -1L) {
//                        val updated = newsDao.insertNews(item)
//                        if (updated > 0) {
//                            needsUpdate = true
//
//                        }
//
//                    } else {
//                        needsUpdate = true
//                    }
//                }
//                needsUpdate
//            }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe { needsUpdate ->
//                    if (needsUpdate) {
//                        fetchNewsOffline()
//                    }
//                }
//        }
//
//}




